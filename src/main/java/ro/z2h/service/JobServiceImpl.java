package ro.z2h.service;

import ro.z2h.dao.JobDao;
import ro.z2h.domain.Job;
import ro.z2h.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Buli on 11/13/2014.
 */
public class JobServiceImpl implements JobService {
    @Override
    public List<Job> getAllJobs() {
        JobDao jobDao = new JobDao();
        ArrayList<Job> jobsFromDB = null;

        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection con = dbConnection.getConnection();

        try {
            jobsFromDB = jobDao.getAllJobs(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobsFromDB;
    }
}
