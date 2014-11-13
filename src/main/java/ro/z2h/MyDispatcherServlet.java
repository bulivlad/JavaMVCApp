package ro.z2h;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethAttributes;
import ro.z2h.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by Buli on 11/11/2014.
 */
public class MyDispatcherServlet extends HttpServlet {

    private Object controlere;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getParameter()
        Object reply = dispatch(req,resp);


        reply(reply,req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Delegate to someone (an application controller)*/
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Delegate to someone (an application controller)*/
        dispatchReply("POST", req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("DELETE", req, resp);
    }

    private void dispatchReply(String httpMethod, HttpServletRequest req, HttpServletResponse resp){
        /*Dispatch - Delegare catre un application controller si asteptarea unui raspuns*/

        Object r = dispatch(req,resp);

        /*Trimiterea raspunsului catre client*/
        try {
            reply(r, req, resp);
        } catch (IOException e) {
            /*Transmiterea erorilor*/
            sendException(e, req, resp);
        }

    }

    private void sendException(Exception ex, HttpServletRequest req, HttpServletResponse resp) {
        /*Tratare exceptie*/
    }

    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException{
        PrintWriter out = resp.getWriter();
        ObjectMapper newJackson = new ObjectMapper();
        String valueAsString = newJackson.writeValueAsString(r);
        out.println(valueAsString);
    }

    /*Unde ar trebui apelat un app controller*/
    private Object dispatch(HttpServletRequest req, HttpServletResponse resp){
        String pathInfo = req.getPathInfo();

        /*Stabilire controller in functie de pathInfo*/
//        if(pathInfo.startsWith("/employee")) {
//            EmployeeController controller = new EmployeeController();
//            return controller.getAllEmployees();
//        }
//        else if(pathInfo.startsWith("/department")) {
//            DepartmentController controller = new DepartmentController();
//            return controller.getAllDepartments();
//        }

        MethodAttributes mapValue = methodMap.get(pathInfo);
        String controllerName;

        //String id = req.getParameter("idEmployee");


        try {
            controllerName = mapValue.getControllerClass();
        }catch(NullPointerException e){
            e.printStackTrace();
            return "HELLO";
        }
        Object invoke = null;

        try {
            Class newController = Class.forName(controllerName);
            Object appControllerInstance = newController.newInstance();
            Method newControllerMethod = newController.getMethod(mapValue.getMethodName(),mapValue.getMethodParametersType());
            Parameter[] newControllerMethodParameters = newControllerMethod.getParameters();
            ArrayList<String> parametersValue = new ArrayList<>();
            for (Parameter newControllerMethodParameter : newControllerMethodParameters) {
                parametersValue.add(req.getParameter(newControllerMethodParameter.getName()));
            }
            invoke = newControllerMethod.invoke(appControllerInstance, (String[])parametersValue.toArray(new String[0]));
//            invoke = newControllerMethod.invoke(appControllerInstance,id);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return invoke;
    }

    private HashMap<String,MethodAttributes> methodMap = new HashMap<String, MethodAttributes>();

    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.z2h.controller");

            for (Class aClass : classes) {
                if(aClass.isAnnotationPresent(MyController.class)){
                    Annotation controllerAnnotation = aClass.getAnnotation(MyController.class);
                    System.out.println(((MyController) controllerAnnotation).urlPath());
                    Method[] aClassMethods = aClass.getMethods();
                    for (Method aClassMethod : aClassMethods) {
                        if(aClassMethod.isAnnotationPresent(MyRequestMethod.class)){
                            MyRequestMethod methodAnnotation = aClassMethod.getAnnotation((MyRequestMethod.class));
                            System.out.println(methodAnnotation.urlPath() + methodAnnotation.methodType().toString());

                            MethodAttributes hashMapValue = new MethodAttributes();
                            hashMapValue.setControllerClass(aClass.getName());
                            hashMapValue.setMethodName(aClassMethod.getName());
                            hashMapValue.setMethodType(methodAnnotation.methodType());
                            hashMapValue.setMethodParametersType(aClassMethod.getParameterTypes());
                            methodMap.put(((MyController) controllerAnnotation).urlPath() + methodAnnotation.urlPath(),hashMapValue);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
