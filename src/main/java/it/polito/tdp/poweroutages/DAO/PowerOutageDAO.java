package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	
	public List<PowerOutages> getPowerOutagesNercList(Nerc nerc) {
		
		String sql = "SELECT id, nerc_id, date_event_began, date_event_finished, customers_affected "
				+ "FROM poweroutages "
				+ "WHERE nerc_id = ?";
		List<PowerOutages> powerOutagesNercList = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				PowerOutages p = new PowerOutages(rs.getInt("id"), 
						rs.getInt("customers_affected"),
						rs.getTimestamp("date_event_began").toLocalDateTime(),
						rs.getTimestamp("date_event_finished").toLocalDateTime(),
						nerc);

				powerOutagesNercList.add(p);
			}

			conn.close();
			return powerOutagesNercList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		
	}

}
