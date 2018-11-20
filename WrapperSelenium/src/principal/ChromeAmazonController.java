package principal;

import java.util.Arrays;
import java.util.List;

import javax.naming.directory.DirContext;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaz.GridLibros;

public class ChromeAmazonController {
	
	private static WebDriver driver= null;

	public ChromeAmazonController( String elemento_a_buscar) {
		String exePath = "C:\\Users\\albert\\software\\selenium_drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("http://www.amazon.es/");
		
		/* Detecto los cookies
		 * 
		WebElement ventanaCookies = driver.findElement(By.xpath("/html/body/aside/div/button"));
		if (ventanaCookies != null){
			System.out.println("Detectado caja de cookies");
			ventanaCookies.click();}
		
		*/
		driver.manage().deleteAllCookies();

		
		// Filtro para ver solo los libros
		WebElement desplegable = driver.findElement(By.id("searchDropdownBox"));
		desplegable.click();
		WebElement opcionLibros = driver.findElement(By.cssSelector("#searchDropdownBox > option:nth-child(25)"));
		opcionLibros.click();
		
		/* Busco en el buscador de la web */
		WebElement cajaBusqueda = driver.findElement(By.id("twotabsearchtextbox"));
		cajaBusqueda.sendKeys(elemento_a_buscar);
		cajaBusqueda.submit();
		
		try {
			Thread.sleep(10000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Guardamos los elementos resultantes de la busqueda
		//List<WebElement> listaElementos = driver.findElements(By.xpath("//*[]"));
		//List<WebElement> listaElementos = driver.findElements(By.id("s-results-list-atf"));
		List<WebElement> listaElementos = driver.findElements(By.xpath("//*[contains(@class, 's-result-item celwidget')]"));
		System.out.println("elementos = " + listaElementos.toString());
		System.out.println("Número de elementos de la lista: " + listaElementos.size() );	
		/*// Obtener cada uno de los artículos
		WebElement elementoActual, navegacion, precio, descuento, autor;
		String descuentoString = "null", autorString= "null";
		int j=1;
		
		for (int i=0; i<listaElementos.size() && i < 16; i++)	{
			//obtengo los diferentes elementos buscados
			elementoActual = listaElementos.get(i);
			navegacion = elementoActual.findElement(By.xpath("//*[@id=\"result_"+i+"\"]/div/div/div/div[2]/div[1]/div[1]/a/h2"));
			precio = elementoActual.findElement(By.xpath("//*[@id=\"result_"+i+"\"]/div/div/div/div[2]/div[2]/div[1]/div[2]/a/span[2]"));
			autor = elementoActual.findElement(By.xpath("//*[@id=\"result_"+i+"\"]/div/div/div/div[2]/div[1]/div[2]"));
			System.out.println(navegacion.getText()+"  "+precio.getText()+"  "+ autor.getText());
				
		}*/
		
		GridLibros gridLibros = new GridLibros(getInstanciaLibro(listaElementos, 0));
		gridLibros.setVisible(true);
				
	}
	
	private static InstanciaLibro getInstanciaLibro(List<WebElement> listaElementos, int num_elemento) {
		WebElement navegacion, precio, autor;
		WebElement elemento = listaElementos.get(num_elemento);
		navegacion = elemento.findElement(By.xpath("//*[@id=\"result_"+num_elemento+"\"]/div/div/div/div[2]/div[1]/div[1]/a/h2"));
		precio = elemento.findElement(By.xpath("//*[@id=\"result_"+num_elemento+"\"]/div/div/div/div[2]/div[2]/div[1]/div[2]/a/span[2]"));
		autor = elemento.findElement(By.xpath("//*[@id=\"result_"+num_elemento+"\"]/div/div/div/div[2]/div[1]/div[2]"));
		
		
		
		return new InstanciaLibro("Amazon", navegacion.getText(), autor.getText(), convertirPrecioADouble(precio.getText()), null);
	}
	
	private static Double convertirPrecioADouble(String precio) {
		String[] split = precio.split(" ");
		//String precioStr = split[1].trim();
		String precioStr = formatearStirngDouble(split[1]);
		Double res = Double.parseDouble(precioStr);
		return res;
	}
	private static String formatearStirngDouble(String precio) {
		String[] split = precio.split(",");
		String res = split[0] + "." + split[1];
		return res;
	}
}