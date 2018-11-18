package principal;

//import java.awt.event.HierarchyBoundsAdapter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxFNACController {
	
	private static WebDriver driver= null;
	private InstanciaLibro instanciaLibro = new InstanciaLibro();
	
	public FirefoxFNACController(String elemento_a_buscar) {
		String exePath = "C:\\Users\\albert\\software\\selenium_drivers\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", exePath);
		driver = new FirefoxDriver();
		driver.get("http://www.fnac.es");
		driver.manage().window().maximize();
		
		
		// Detecto los cookies
		WebElement ventanaCookies = driver.findElement(By.xpath("/html/body/aside/div/button"));
		if (ventanaCookies != null){
			System.out.println("Detectado caja de cookies");
			ventanaCookies.click();}
		

		//Espero a que se muestren la opcion de libros
		WebDriverWait waiting;
		waiting = new WebDriverWait(driver, 3);
		waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".select-target")));
		
		// pulsamos para buscar las secciones
		WebElement desplegable = driver.findElement(By.cssSelector(".select-target"));
		if (desplegable != null) {
			desplegable.click();
			WebElement libros = driver.findElement(By.cssSelector("li.select-option:nth-child(2)"));
			System.out.println("Pulsando sobre el desplegable");
		}

		//Espero a que se muestren la opcion de libros
		WebDriverWait waitingLibros;
		waitingLibros = new WebDriverWait(driver, 3);
		waitingLibros.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.select-option:nth-child(2)")));
		
		// pulsamos para buscar las secciones
		WebElement librosOpcion = driver.findElement(By.cssSelector("li.select-option:nth-child(2)"));
		if (librosOpcion != null) {
			librosOpcion.click();
			System.out.println("Pulsando sobre libros");
		}
		
		
		/* Busco en el buscador de la web */
		WebElement cajaBusqueda = driver.findElement(By.id("Fnac_Search"));
		cajaBusqueda.sendKeys(elemento_a_buscar);
		cajaBusqueda.submit();
		
		try {
			Thread.sleep(5000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Guardamos los elementos resultantes de la busqueda
		List<WebElement> listaElementos = driver.findElements(By.xpath("//*[contains(@class, 'Article-itemGroup')]"));
		
		
		
		System.out.println("Número de elementos de la lista: " + listaElementos.size() );
	
		// Obtener cada uno de los artículos
		WebElement elementoActual, navegacion, precio, descuento, autor;
		String descuentoString = "null", autorString= "null";
		int j=1;
		
		for (int i=0; i<listaElementos.size() && i < 20; i++)	{
			//obtengo los diferentes elementos buscados
			elementoActual = listaElementos.get(i);
			navegacion = elementoActual.findElement(By.className("js-minifa-title"));
			precio = elementoActual.findElement(By.className("userPrice"));
		
			//Busco descuentos
		try {
				descuento = elementoActual.findElement(By.className("stimuliOPC"));
				descuentoString = descuento.getText();
			}
		catch(Exception e){
			descuentoString = "no hay descuento";
		}
				
			
		// Busco autores
		try {
			autor = elementoActual.findElement(By.xpath("/html/body/div[4]/div/div[7]/ul/li["+i+"]/div/div[2]/div/p[2]/a"));
			autorString = autor.getText();
		}
		catch(Exception e){
		autorString = "no hay autor";
	}
			System.out.println(j + " " + navegacion.getText()+" "+precio.getText()+" "+ descuentoString + autorString );
			j++;
			
		}
		
		try {
			Thread.sleep(10000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		/*instanciaLibro.setDescunetoLibro(Double.parseDouble(descuento.getText()));
		instanciaLibro.setNombreAutorLibro(autor.getText());
		instanciaLibro.setPrecioLibro(Double.parseDouble(precio.getText()));
		instanciaLibro.setSitio("Fnac");
		instanciaLibro.setTituloLibro("titulo desconocido");
		//navegacion, precio, descuento, autor;*/
	}
	
	public InstanciaLibro getInstanciaLibro() {
		return instanciaLibro;
	}
	
	private void inicializarIntaniaLibro(WebElement element) {
		
	}

}
