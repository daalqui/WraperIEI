package principal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeAmazonController {
	
	private static WebDriver driver= null;

	public ChromeAmazonController( String elemento_a_buscar) {
		String exePath = "C:\\Users\\David\\Desktop\\UPV\\4� Informatica\\IEI\\Pract selenium\\chromedriver.exe";
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
			Thread.sleep(5000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Guardamos los elementos resultantes de la busqueda
		List<WebElement> listaElementos = driver.findElements(By.id("s-results-list-atf"));
				
		System.out.println("N�mero de elementos de la lista: " + listaElementos.size() );
			
		// Obtener cada uno de los art�culos
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
				
		}
	}

}