package ejercicio1.dao;

import java.sql.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.ConfigHibernate;
import ejercicio1.entidad.Usuario;

public class DaoUsuario {
	private static ConfigHibernate cHibernate;
	private static Session session;
	
	
	public static void ReadUsuarioByID(int id) {
		cHibernate = new ConfigHibernate();
		session = cHibernate.abrirConexion();
		Query query = session.createQuery("FROM Usuario u WHERE u.id = :id");
		query.setParameter("id", id);
		
		Usuario usuario = (Usuario)query.uniqueResult();
		
		usuario.getDatosPersonales().setFechaNacimiento(formatDate(usuario.getDatosPersonales().getFechaNacimiento()));
		System.err.println("Usuario con id " + id +  ": ");
		System.out.print(usuario.getDatosPersonales().getPais().toString());
		
		cHibernate.cerrarSession();
	}

	public static void Add(Usuario usuario) {
		cHibernate = new ConfigHibernate();
		session = cHibernate.abrirConexion();
		session.beginTransaction();
		session.save(usuario);		
		session.getTransaction().commit();
		cHibernate.cerrarSession();
	}
	
	protected static Date formatDate(Date date) {
		date.setMonth(date.getMonth()-1);
		date.setYear(date.getYear()-1900);
		return date;
	}
}
