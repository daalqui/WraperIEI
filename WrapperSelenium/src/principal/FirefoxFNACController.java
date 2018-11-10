package principal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxFNACController {
	
	private static WebDriver driver= null;
	
	
	public FirefoxFNACController(String elemento_a_buscar) {
		String exePath = "C:\\Users\\David\\Desktop\\UPV\\4º Informatica\\IEI\\Pract selenium\\geckodriver.exe";
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
			
			if(elementoActual.findElement(By.className("stimuliOPC-flyer.stimuliOPC-flyer--GoodDeal")) != null) {
				descuento = elementoActual.findElement(By.className("stimuliOPC-flyer.stimuliOPC-flyer--GoodDeal"));
				descuentoString = descuento.getText();
			}

				//autor = elementoActual.findElement(By.xpath("/html/body/div[4]/div/div[6]/ul/li["+j+"]"+"/div/div[2]/div/p[2]/a"));
			
			
			System.out.println(j + " " + navegacion.getText()+" "+precio.getText()+" "+ descuentoString );
			j++;
			
		}
		
		try {
			Thread.sleep(10000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
