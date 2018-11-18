package principal;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxFNACController {
	
	private static WebDriver driver= null;
	private WebDriverWait waiting;
	
	public FirefoxFNACController(String elemento_a_buscar) {
		String exePath = "C:\\Users\\David\\Desktop\\UPV\\4� Informatica\\IEI\\Pract selenium\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", exePath);
		driver = new FirefoxDriver();
		driver.get("http://www.fnac.es");
		driver.manage().window().maximize();
		waiting = new WebDriverWait(driver, 5);
		
		// Detecto los cookies
		WebElement ventanaCookies = driver.findElement(By.xpath("/html/body/aside/div/button"));
		if (ventanaCookies != null){
			System.out.println("Detectado caja de cookies");
			ventanaCookies.click();}
		

		//Espero al desplegable
		waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".select-target")));
		
		// pulsamos para buscar las secciones
		WebElement desplegable = driver.findElement(By.cssSelector(".select-target"));
		if (desplegable != null) {
			desplegable.click();
			System.out.println("Pulsando sobre el desplegable");
		}

		//Espero a que se muestren la opcion de libros
		waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.select-option:nth-child(2)")));
		
		// pulsamos la opcion de libros
		WebElement librosOpcion = driver.findElement(By.cssSelector("li.select-option:nth-child(2)"));
		if (librosOpcion != null) {
			librosOpcion.click();
			System.out.println("Pulsando sobre libros");
		}
		
		
		/* Busco en el buscador de la web */
		WebElement cajaBusqueda = driver.findElement(By.id("Fnac_Search"));		
		cajaBusqueda.sendKeys(elemento_a_buscar);
		cajaBusqueda.submit();
		
		

		//Espero al desplegable de elementos por p�gina
		waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.Item-onPage:nth-child(1) > div:nth-child(3)")));
		
		// pulsamos en  el desplegable 
		WebElement desplegable_elementos_pagina = driver.findElement(By.cssSelector("span.Item-onPage:nth-child(1) > div:nth-child(3)"));
		if (desplegable_elementos_pagina != null) {
			desplegable_elementos_pagina.click();
			System.out.println("Pulsando sobre el desplegable de elementos por p�gina");
		}

		//Espero a la opci�n de 100 elementos por p�gina
		waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".actived > ul:nth-child(2) > li:nth-child(3)")));
		
		// pulsamos en  el desplegable 
		WebElement opcion_100_elementos = driver.findElement(By.cssSelector(".actived > ul:nth-child(2) > li:nth-child(3)"));
		if (opcion_100_elementos != null) {
			opcion_100_elementos.click();
			System.out.println("Pulsando la opci�n de 100 elementos por p�gina");			
		}
		try {
			Thread.sleep(5000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement botonSiguiente = driver.findElement(By.cssSelector("li.nextLevel1:nth-child(3) > a:nth-child(1)"));
		// Guardamos los elementos resultantes de la busqueda
		do{
		waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'Article-itemGroup')]")));
		List<WebElement> listaElementos = driver.findElements(By.xpath("//*[contains(@class, 'Article-itemGroup')]"));
		
		System.out.println("N�mero de elementos de la lista: " + listaElementos.size() );
	
		// Obtener cada uno de los art�culos
		WebElement elementoActual, navegacion, precio, descuento, autor;
		String descuentoString = "null", autorString= "null";
		int j=1;
		
		for (int i=0; i<listaElementos.size(); i++)	{
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
		//PROVISIONAL HASTA PONER LA ESPERA
		try {Thread.sleep(5000);} 
		catch(InterruptedException e) {e.printStackTrace();}
		
		if (botonSiguiente != null) botonSiguiente.click();
		//PROVISIONAL HASTA PONER LA ESPERA
		try {Thread.sleep(5000);} 
		catch(InterruptedException e) {e.printStackTrace();}
		
		}
		while(botonSiguiente != null);
	}


}
