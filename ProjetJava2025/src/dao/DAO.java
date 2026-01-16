package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

import Projet.Planete;
import Projet.Vecteur;
import Projet.Constantes;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class DAO {

    private static String databaseName = "corps celestes";
    private static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?serverTimezone=UTC";
    private static String login = "root"; 
    private static String password = "";

    // Méthode utilitaire pour obtenir une connexion
    private static Connection getConnection() throws SQLException {
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(url);
        mysqlDS.setUser(login);
        mysqlDS.setPassword(password);
        return mysqlDS.getConnection();
    }

    // PLANETE

    public static void ajouterElement(String nom, String table) {
        String sqlQuery = "INSERT INTO `" + table + "`(`nom`) VALUES (?)";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout");
            e.printStackTrace();
        }
    }

    public static void suppElement(String nom, String table) {
        String sqlQuery = "DELETE FROM `" + table + "` WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la suppression");
            e.printStackTrace();
        }
    }

    public static void setMasse(String nom, double masse, String table) {
        String sqlQuery = "UPDATE `" + table + "` SET masse_kg = ? WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setDouble(1, masse);
            st.setString(2, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la mise à jour de la masse");
            e.printStackTrace();
        }
    }

    public static void setRayon(String nom, double rayon, String table) {
        String sqlQuery = "UPDATE `" + table + "` SET rayon_km = ? WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setDouble(1, rayon);
            st.setString(2, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la mise à jour du rayon");
            e.printStackTrace();
        }
    }

    public static void setTemp(String nom, double temp) {
        String sqlQuery = "UPDATE `planetes` SET temperature = ? WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setDouble(1, temp);
            st.setString(2, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la mise à jour de la température");
            e.printStackTrace();
        }
    }

    public static void setTempSur(String nom, double temp) {
        String sqlQuery = "UPDATE `etoile` SET temperature_surface = ? WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setDouble(1, temp);
            st.setString(2, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la mise à jour de la température de surface");
            e.printStackTrace();
        }
    }

    public static void addComp_Atm(String nom, String compo) {
        String sqlQuery = "UPDATE planetes SET comp_atm = JSON_ARRAY_APPEND(comp_atm, '$', ?) WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, compo);
            st.setString(2, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout de composant atmosphérique");
            e.printStackTrace();
        }
    }

    public static void suppComp_Atm(String nom, String compo) {
        String sqlQuery = "UPDATE planetes SET comp_atm = JSON_REMOVE(comp_atm, JSON_UNQUOTE(JSON_SEARCH(comp_atm, 'one', ?))) WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, compo);
            st.setString(2, nom);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la suppression de composant atmosphérique");
            e.printStackTrace();
        }
    }

    public static double getMasse(String nom, String table) {
        String sqlQuery = "SELECT masse_kg FROM " + table + " WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("masse_kg");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération de la masse");
            e.printStackTrace();
        }
        return -1;
    }

    public static double getRayon(String nom, String table) {
        String sqlQuery = "SELECT rayon_km FROM " + table + " WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // CORRECTION: Conversion km → m
                return rs.getDouble("rayon_km") * Constantes.KM_TO_M;
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération du rayon");
            e.printStackTrace();
        }
        return -1;
    }

    public static double getTemp(String nom) {
        String sqlQuery = "SELECT temperature FROM planetes WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("temperature");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération de la température");
            e.printStackTrace();
        }
        return -1;
    }

    public static double getTempSur(String nom) {
        String sqlQuery = "SELECT temperature_surface FROM etoile WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("temperature_surface");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération de la température de surface");
            e.printStackTrace();
        }
        return -1;
    }

    public static String getComp_Atm(String nom) {
        String sqlQuery = "SELECT comp_atm FROM planetes WHERE nom = ?";
        try (Connection cn = getConnection();
             PreparedStatement st = cn.prepareStatement(sqlQuery)) {
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("comp_atm");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération de la composition atmosphérique");
            e.printStackTrace();
        }
        return "";
    }

    public static Planete loadPlanete(String nom) {
        double masse = getMasse(nom, "planetes");
        double rayon = getRayon(nom, "planetes");
        double temperature = getTemp(nom);
        
        System.out.println("Chargement planète " + nom + ": masse=" + masse + ", rayon=" + rayon + ", temp=" + temperature);
        
        if (masse < 0 || rayon < 0 || temperature < 0) {
            System.err.println("Erreur: Planète '" + nom + "' introuvable dans la DB");
            return null;
        }

        Map<String, Double> atmosphere = new HashMap<>();
        String json = getComp_Atm(nom);

        if (json.contains("O2")) atmosphere.put("O2", 21.0);
        if (json.contains("N2")) atmosphere.put("N2", 78.0);

        return new Planete(
            masse,
            rayon,
            new Vecteur(1.496e11, 0),  // Position initiale
            new Vecteur(0, 29780),     // Vitesse initiale
            atmosphere,
            101_325,
            temperature
        );
    }
   

   


    public static void main(String[] args) {
        double i = getTempSur("Soleil");
        System.out.println(i);
    }


		
	

	


	


	

	



		


	

	
	

	 public static ArrayList<String> getNomsPlanetes() {
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
	        ArrayList<String> planetes = new ArrayList<>();
	        ArrayList<String> erreur = new ArrayList<>();
	        

	        
			try {
				st = cn.createStatement();
				String sqlQuery = "SELECT nom FROM planetes";
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					planetes.add(rs.getString("nom"));
				}
				return planetes;
			}
			catch(SQLException e) {
				System.err.println("Erreur requete SQL");
				e.printStackTrace();
			}
			return erreur;
	    }
}





	


	








