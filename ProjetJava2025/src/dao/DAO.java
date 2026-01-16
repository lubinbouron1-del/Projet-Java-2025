package dao;




import java.util.List;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

import Projet.Planete;
import Projet.Vecteur;
import java.util.Map;
import java.util.HashMap;




public class DAO {


	private static String databaseName="corps celestes";
	private static String url="jdbc:mysql://localhost:3306/"+databaseName+"?serverTimezone=UTC";
	private static String login="root"; 
	private static String password="";

	//PLANETE

	public static void ajouterElement(String nom,String table) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "INSERT INTO `"+table+"`( `nom`) VALUES ('"+nom+"')";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}

	}

	public static void suppElement(String nom, String table) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "DELETE FROM `"+table+"` WHERE nom = '"+nom+"'";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}

	}

	public static void setMasse(String nom,double masse, String table) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "UPDATE `"+table+"` SET masse_kg = "+masse+" WHERE nom = '"+nom+"'";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}

	}

	public static void setRayon(String nom,double rayon, String table) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "UPDATE `"+table+"` SET rayon_km = "+rayon+" WHERE nom = '"+nom+"'";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}
	}




	public static void setTemp(String nom,double temp) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "UPDATE `planetes` SET temperature = "+temp+" WHERE nom = '"+nom+"'";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}
	}


	public static void setTempSur(String nom,double temp) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "UPDATE `etoile` SET temperature_surface = "+temp+" WHERE nom = '"+nom+"'";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}
	}

	public static void addComp_Atm(String nom,String compo) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "UPDATE planetes SET comp_atm = JSON_ARRAY_APPEND(comp_atm, '$', '"+compo+"') WHERE nom = '"+nom+"'";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}
	}


	public static void suppComp_Atm(String nom,String compo) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "UPDATE planetes SET comp_atm = JSON_REMOVE(comp_atm,JSON_UNQUOTE(JSON_SEARCH(comp_atm, 'one', '"+compo+"')))WHERE nom = '"+nom+"'";
			int i = st.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();

		}
	}


	public static double getMasse(String nom, String table) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "SELECT masse_kg FROM "+table+" WHERE nom = '"+nom+"'";
			rs = st.executeQuery(sqlQuery);
			if(rs.next())
				return rs.getDouble("masse_kg");
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
		return -1;
	}	

	public static double getRayon(String nom, String table) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "SELECT rayon_km FROM "+table+" WHERE nom = '"+nom+"'";
			rs = st.executeQuery(sqlQuery);
			if(rs.next())
				return rs.getDouble("rayon_km");
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
		return -1;
	}	



	public static double getTemp(String nom) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "SELECT temperature FROM planetes WHERE nom = '"+nom+"'";
			rs = st.executeQuery(sqlQuery);
			if(rs.next())
				return rs.getDouble("temperature");
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
		return -1;
	}	


	public static double getTempSur(String nom) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "SELECT temperature_surface FROM etoile WHERE nom = '"+nom+"'";
			rs = st.executeQuery(sqlQuery);
			if(rs.next())
				return rs.getDouble("temperature_surface");
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
		return -1;
	}

	public static String getComp_Atm(String nom) {
		Connection cn=null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		try {
			cn = mysqlDS.getConnection();
		} 
		catch (SQLException e1) {
			System.err.println("Erreur de parcours de connexion");
			e1.printStackTrace();
		}
		try {
			st = cn.createStatement();
			String sqlQuery = "SELECT comp_atm FROM planetes WHERE nom = '"+nom+"'";
			rs = st.executeQuery(sqlQuery);
			if(rs.next())
				return rs.getString("comp_atm");
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
		return "";
	}	
	public static Planete loadPlanete(String nom) {

	    double masse = getMasse(nom, "planetes");
	    double rayon = getRayon(nom, "planetes");
	    double temperature = getTemp(nom);

	    Map<String, Double> atmosphere = new HashMap<>();
	    String json = getComp_Atm(nom);

	    // Parsing simple (à améliorer plus tard)
	    if (json.contains("O2")) atmosphere.put("O2", 21.0);
	    if (json.contains("N2")) atmosphere.put("N2", 78.0);

	    return new Planete(
	        masse,
	        rayon,
	        new Vecteur(0, 0),
	        new Vecteur(0, 0),
	        atmosphere,
	        101_325,
	        temperature
	    );
	}







	public static void main(String[] args) {

		double i = getTempSur("Soleil");
		System.out.println(i);

	}

}





