package d.assign.production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Handle {
	public static void main(String[] args) throws Exception {
		String state = "Ohio";
		String[] counties = "Hamilton, Butler, Brown, Highland, Clermont, Scioto, Switzerland".split(",");

		List<P> ch7 = new ArrayList<>();
		ch7.add(new P("Chapter7", "Leon Hewitt"));
		ch7.add(new P("Chapter7", "Joseph Luken"));
		ch7.add(new P("Chapter7", "Leon Hewitt"));

		List<P> ch13 = new ArrayList<>();
		ch13.add(new P("Chapter13", "Leon Hewitt"));
		ch13.add(new P("Chapter13", "Joseph Luken"));
		ch13.add(new P("Chapter13", "Leon Hewitt"));

		Map<String, String> map = getPaId(ch13, ch7);
		System.out.println(map);

		for (String county : counties) {

			PreparedStatement pst = getConnect().prepareStatement("SELECT id FROM  ul_crm.`ul_partner_area` WHERE state = ? AND county =?;");
			pst.setString(1, state.trim());
			pst.setString(2, county.trim());
			ResultSet result = pst.executeQuery();
			if(result.next()) {
				Long id = result.getLong("id");
				System.out.println(state + ":" + county + ":" + result.getLong("id"));
				int c7 = 1;
				String sql = "insert into ul_partner_relations(area_id ,partner_id, partner_name , product , sequence_number)"
						+ " values(?,  ? , ? , ? , ?)";
				for (P p : ch7) {
					PreparedStatement pss = getConnect().prepareStatement(sql);
					pss.setLong(1, id);
					pss.setString(2, map.get(p.name));
					pss.setString(3, p.name);
					pss.setString(4, p.product);
					pss.setInt(5, c7);
					System.out.println( id + ":" + map.get(p.name) + ":" + p.name + ":" + p.product + ":" + c7);
					pss.execute();
					c7++;
				}

				int c13 = 1;
				for (P p : ch13) {
					PreparedStatement pss = getConnect().prepareStatement(sql);
					pss.setLong(1, id);
					pss.setString(2, map.get(p.name));
					pss.setString(3, p.name);
					pss.setString(4, p.product);
					pss.setInt(5, c13);
					System.out.println( id + ":" + map.get(p.name) + ":" + p.name + ":" + p.product + ":" + c13);
					pss.execute();
					c13++;
				}
			}

		}

	}

	static Map<String, String> getPaId(List<P> list1 , List<P> list2) throws Exception {
		Set<String> set = new HashSet<>();
		for (P p : list1) {
			set.add(p.name);
		}

		for (P p : list2) {
			set.add(p.name);
		}
		Map<String, String> map = new HashMap<>();
		String sql = "SELECT id , CONCAT( first_name  , ' ' , last_name) name FROM ul_crm.`ul_partner` WHERE CONCAT( first_name  , ' ' , last_name) IN ('"
				+ set.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(", ", "','") +  "')";
		System.out.println(sql);
		ResultSet result = getConnect().createStatement().executeQuery(sql);
		while(result.next()) {
			map.put(result.getString("name"), result.getString("id"));
		}
		return map;
	}

	static ThreadLocal<Connection> conn = new ThreadLocal<>();

	static Connection getConnect() throws Exception {
		Connection c = conn.get() ;
		if (c == null) {
			String url = "jdbc:mysql://uprightlaw-payment-production.crch0eknj48f.us-east-1.rds.amazonaws.com:3306/ul_crm?useUnicode=true&amp;connectTimeout=10000&amp;socketTimeout=3000&amp;characterEncoding=UTF-8allowMultiQueries=true";
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(url, "root", "");
			conn.set(c);
		}

		return c;
	}

}
class P {
	String product;
	String name;
	public P(String product, String name) {
		super();
		this.product = product;
		this.name = name;
	}

}
