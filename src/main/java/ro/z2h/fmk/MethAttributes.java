package ro.z2h.fmk;

/**
 * Created by Buli on 11/11/2014.
 */
public class MethAttributes {

    private String controllerName;
    private String methodName;
    private String httpMethod;

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethodGet) {
        this.httpMethod = httpMethodGet;
    }
}
