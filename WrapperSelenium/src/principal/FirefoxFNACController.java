package principal;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaz.GridLibros;

public class FirefoxFNACController {
    
    private static WebDriver driver= null;
    private WebDriverWait waiting;
    private WebElement botonSiguiente;

    
    
    public  FirefoxFNACController(String elemento_a_buscar) {
        List<InstanciaLibro> libros = new ArrayList<InstanciaLibro>();
        String exePath = "C:\\Users\\albert\\software\\selenium_drivers\\geckodriver.exe";
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
        System.out.println("Busco el nombre: " + elemento_a_buscar);
        WebElement cajaBusqueda = driver.findElement(By.id("Fnac_Search"));     
        cajaBusqueda.sendKeys(elemento_a_buscar);
        cajaBusqueda.submit();
  
        
        // Guardamos los elementos resultantes de la busqueda
        do{
           
        waiting.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'Article-itemGroup')]")));
        List<WebElement> listaElementos = driver.findElements(By.xpath("//*[contains(@class, 'Article-itemGroup')]"));
        
        System.out.println("Número de elementos de la lista: " + listaElementos.size() );
    
        // Obtener cada uno de los artículos
        WebElement elementoActual, navegacion, precio, descuento, autor;
        String descuentoString = "null", autorString= "null";
        int j=1;
        
        for (int i=0; i<listaElementos.size(); i++) {
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
            descuentoString = null;
        }
                
            
        // Busco autores
        try {
            autor = elementoActual.findElement(By.xpath("/html/body/div[4]/div/div[7]/ul/li["+i+"]/div/div[2]/div/p[2]/a"));
            autorString = autor.getText();
        }
        catch(Exception e){
        autorString = null;
        }
        	String precioString = precio.getText();
        	InstanciaLibro instanciaLibro = new InstanciaLibro();
        	//System.out.println(precioString);
        	String precioStringFormateado = precioString.substring(0,precioString.length()-1);
        	//System.out.println(precioStringFormateado);
        	/*Double precioLibro = Double.parseDouble(
        			ConvertidorDeString.formatearStirngDouble(precioStringFormateado));*/
        	instanciaLibro.setPrecioLibro(precioString);
        	//String precioStringFormateado = precioString.substring(0,precioString.length()-1);
        	/*Double descuentoLibro = Double.parseDouble(
                	ConvertidorDeString.formatearStirngDouble(precioStringFormateado));*/
        	//System.out.println(precioString.substring(0, descuentoString.length()-1));
        	/*Double descuentoLibro = Double.parseDouble(
        	ConvertidorDeString.formatearStirngDouble(precioString.substring(0, descuentoString.length()-1)));*/
        	instanciaLibro.setDescunetoLibro(descuentoString);
        	instanciaLibro.setNombreAutorLibro(autorString);
        	//String precioStringFormateado = precioString.substring(0,precioString.length()-1);
        	/*Double precioLibro = Double.parseDouble(
                	ConvertidorDeString.formatearStirngDouble(precioStringFormateado));*/
        	/*Double precioLibro = Double.parseDouble(
        	ConvertidorDeString.formatearStirngDouble(precioString.substring(0, precioString.length())));*/
        	//instanciaLibro.setPrecioLibro(precioLibro);
        	instanciaLibro.setSitio("Fnac");
        	instanciaLibro.setTituloLibro(navegacion.getText());
        	libros.add(instanciaLibro);
            /*libros.add(new InstanciaLibro("Fnac", navegacion.getText(), autorString,
            		Double.parseDouble(precioString.substring(0, precioString.length())), 
            		Double.parseDouble(precioString.substring(0, descuentoString.length()))));*/
            System.out.println(j + " " + navegacion.getText()+" "+precio.getText()+" "+ descuentoString + autorString );
            j++;
            /*String sitio, String tituloLibro, String nombreAutorLibro, Double precioLibro,
			Double descunetoLibro*/
        }
        
       
        //PROVISIONAL HASTA PONER LA ESPERA
        try {Thread.sleep(5000);} 
        catch(InterruptedException e) {e.printStackTrace();}
        
        //Espero al desplegable de elementos por página
        try {
            waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.nextLevel1:nth-child(3) > a:nth-child(1)")));
            botonSiguiente = driver.findElement(By.cssSelector("li.nextLevel1:nth-child(3) > a:nth-child(1)"));
            if (botonSiguiente != null) botonSiguiente.click();
        } catch(Exception e) {System.out.println("última página alcanzada"); botonSiguiente = null;}

        //PROVISIONAL HASTA PONER LA ESPERA
        try {Thread.sleep(5000);} 
        catch(InterruptedException e) {e.printStackTrace();}
        
        }
        while(botonSiguiente != null);
        driver.close();
        
        GridLibros grid = new GridLibros(libros, null);
        grid.setVisible(true);
    }
    





}
