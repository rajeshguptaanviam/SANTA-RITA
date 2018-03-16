package com.net.parking.model.generator;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserIDGenerator implements IdentifierGenerator{
	
	private String generatedId = "";
	private Integer id = 0;
	private ResultSet rs = null;
	private Statement statement = null;
	private Connection connection = null;
	private String query = "select count(user_id) from user;";
	private String prefix = "U";
	
	public UserIDGenerator() {}
	
	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		
        connection = session.connection();

        try {
        	
            statement=connection.createStatement();
            rs=statement.executeQuery(query);

            if(rs.next()) {
                id=rs.getInt(1)+101;
                generatedId = prefix +"_"+ new Integer(id).toString();
                return generatedId;
            }
        } catch (SQLException e) {	e.printStackTrace();	}
        return null;
    }
}