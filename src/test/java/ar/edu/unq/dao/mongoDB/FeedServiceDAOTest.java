package ar.edu.unq.dao.mongoDB;

import ar.edu.unq.epers.unidad5.dao.ProductoDAO;
import ar.edu.unq.epers.unidad5.dao.result.PrecioPromedio;
import ar.edu.unq.epers.unidad5.model.Precio;
import ar.edu.unq.epers.unidad5.model.Producto;
import ar.edu.unq.epers.unidad5.model.Usuario;
import ar.edu.unq.epers.unidad5.model.Zona;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FeedServiceDAOTest {

	private static final String CODIGO_PRODUCTO_1 = "111";
	private static final String CODIGO_PRODUCTO_2 = "222";
	private static final String CODIGO_PRODUCTO_3 = "333";
	private static final String CODIGO_PRODUCTO_4 = "444";
	
	private ProductoDAO dao;
	
	@SuppressWarnings("unchecked")
	private <T> List<T> list(T... elements) {
		return Arrays.asList(elements);
	}
	
	@Before
	public void setup() {
		this.dao = new ProductoDAO();
		
		List<Usuario> usuarios = this.list(new Usuario("Pepe"), new Usuario("Juan"), new Usuario("Cosme"));
		
		List<Zona> zonas = this.list(new Zona("Bernal", "Saens Peña", "3550", "Argentina"),
						new Zona("Quilmes", "Rivadavia", "435", "Argentina"),
						new Zona("Ciudad De Buenos Aires", "Callao", "1500", "Argentina"));
		
		List<Producto> productos = this.list(
						new Producto(CODIGO_PRODUCTO_1, "Triple de chocolate", "Capitan del Espacio"),
						new Producto(CODIGO_PRODUCTO_2, "Dulce de leche 500 grs", "La Serenisima"),
						new Producto(CODIGO_PRODUCTO_3, "Sprite 2.25lts", "Cocacola"),
						new Producto(CODIGO_PRODUCTO_4, "Express x3", "Terrabusi")
		);
				
		IntStream.range(0, 1000).forEach(i -> {;
			productos.forEach(p -> {
				zonas.forEach(z -> {
					usuarios.forEach(u -> {
						Precio precio = new Precio(z, u, i + Integer.parseInt(p.getCodigo()));
						p.addPrecio(precio);
					});
				});
			});
		});
		this.dao.save(productos);
	}
	
	@After
	public void dropAll() {
		//this.dao.deleteAll();
	}
	
	@Test
	public void test_save_and_get_by_id() {
		Zona zonaUS = new Zona("Amazon St.", "1024", "Dellaware", "USA");
		Zona zonaUK = new Zona("Amazon Rd.", "1024", "London", "UK");
		
		Usuario user = new Usuario("claudio");
		
		Producto producto = new Producto("0001", "Longboard", "Santa Cruz");
		producto.addPrecio(new Precio(zonaUS, user, 78));
		producto.addPrecio(new Precio(zonaUK, user, 82));
		this.dao.save(producto);
		
		Assert.assertNotNull(producto.getId());
		
		Producto producto2 = this.dao.get(producto.getId());
		Assert.assertEquals("0001", producto2.getCodigo());
		Assert.assertEquals("Longboard", producto2.getNombre());
		Assert.assertEquals("Santa Cruz", producto2.getMarca());
		Assert.assertEquals(2, producto2.getPrecios().size());
	}
	
	@Test
	public void test_find_by_property() {
		List<Producto> productos = this.dao.getByMarca("Terrabusi");
		
		Assert.assertEquals(1, productos.size());
		Producto producto = productos.get(0);
		
		Assert.assertEquals("444", producto.getCodigo());
		Assert.assertEquals("Express x3", producto.getNombre());
		Assert.assertEquals("Terrabusi", producto.getMarca());
	}
	
	@Test
	public void test_find_by_property_in_collection() {
		List<Producto> productos = this.dao.getByPrecio(446);
		Assert.assertEquals("Todos los productos deben tener algún precio igual a 446", 4, productos.size());

		productos = this.dao.getByPrecio(112);
		Assert.assertEquals("Solo el primer producto debe tener precios menores a 222", 1, productos.size());
	}
	
	@Test
	public void test_find_by_property_in_collection_with_range() {
		List<Producto> productos = this.dao.getByRangoPrecio(100, 500);
		Assert.assertEquals("Todos los productos deben tener algún precio entre 100 y 500", 4, productos.size());

		productos = this.dao.getByRangoPrecio(100, 250);
		Assert.assertEquals("Solo los dos primeros producto debe ntener precios entre 100 y 250", 2, productos.size());
	}
	
	@Test
	public void test_find_by_properties_in_collection() {
		Zona zonaUS = new Zona("Amazon St.", "1024", "Dellaware", "USA");
		Zona zonaUK = new Zona("Amazon Rd.", "1024", "London", "UK");
		
		Usuario user = new Usuario("claudio");
		
		Producto producto1 = new Producto("0001", "Longboard", "Santa Cruz");
		producto1.addPrecio(new Precio(zonaUS, user, 78));
		producto1.addPrecio(new Precio(zonaUK, user, 82));
		this.dao.save(producto1);
		
		Producto producto2 = new Producto("0002", "Skateboard", "Santa Cruz");
		producto2.addPrecio(new Precio(zonaUS, user, 60));
		producto2.addPrecio(new Precio(zonaUK, user, 62));
		this.dao.save(producto2);
		
		List<Producto> productos = this.dao.getPorPrecioEnZona(80, zonaUK);
		Assert.assertEquals("Solo el Skateboard es mas barato en que 80 en UK", 1, productos.size());
	}
	
	@Test
	public void test_aggregations() {
		List<PrecioPromedio> precios = this.dao.getPrecioPromedio(this.list(CODIGO_PRODUCTO_1, CODIGO_PRODUCTO_2));
		
		Assert.assertEquals(CODIGO_PRODUCTO_1, precios.get(0).getCodigo());
		Assert.assertEquals(610, precios.get(0).getValue());
		
		Assert.assertEquals(CODIGO_PRODUCTO_2, precios.get(1).getCodigo());
		Assert.assertEquals(721, precios.get(1).getValue());
	}
	
	
	
}
