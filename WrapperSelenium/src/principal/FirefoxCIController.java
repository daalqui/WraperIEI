package principal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxCIController {
	
	private static WebDriver driver= null;

	public FirefoxCIController( String elemento_a_buscar) {
		String exePath = "C:\\Users\\David\\Desktop\\UPV\\4º Informatica\\IEI\\Pract selenium\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", exePath);
		driver = new FirefoxDriver();
		driver.get("https://www.elcorteingles.es");
		driver.manage().window().maximize();
		
		
		// Detecto los cookies
		driver.manage().deleteAllCookies();
	

		/* Busco en el buscador de la web */
		WebElement cajaBusqueda = driver.findElement(By.id("search-box"));
		cajaBusqueda.sendKeys(elemento_a_buscar);
		cajaBusqueda.submit();
		try {
			Thread.sleep(5000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Filtro para ver solo los libros
		WebElement opcionLibros = driver.findElement(By.xpath("//*[@title='Libros']"));
		opcionLibros.click();
		
		try {
			Thread.sleep(10000);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Guardamos los elementos resultantes de la busqueda
		WebElement numeroLibros = driver.findElement(By.xpath("//*[@id=\"product-list-total\"]"));
				
		System.out.println("Número de elementos de la lista: " + numeroLibros.getText() );
		/*
		// Obtener cada uno de los artículos
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
		*/
	}

}
