package principal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaz.GridLibros;

public class FirefoxCIController {
    
    private static WebDriver driver= null;
    private WebElement botonSiguiente;
    WebElement elementoActual, titulo, precio, descuento, autor;
    private WebDriverWait waiting;
    String descuentoString = "null", autorString= "null";

    public FirefoxCIController( String elemento_a_buscar) {
        String exePath = "C:\\Users\\albert\\software\\selenium_drivers\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", exePath);
        driver = new FirefoxDriver();
        driver.get("https://www.elcorteingles.es");
        driver.manage().window().maximize();
        
        // Borro las cookies
        driver.manage().deleteAllCookies();

        // Detecto los cookies y acepto
        WebElement ventanaCookies = driver.findElement(By.xpath("//*[@id=\"cookies-agree\"]"));
        if (ventanaCookies != null){
            System.out.println("Detectado caja de cookies");
            ventanaCookies.click();}

        /* Busco en el buscador de la web */
        WebElement cajaBusqueda = driver.findElement(By.id("search-box"));
        cajaBusqueda.sendKeys(elemento_a_buscar);
        cajaBusqueda.submit();
    

        //Espero a que se muestren la opcion de libros
        
            
        try {
            Thread.sleep(3000);
        } 
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        // Filtro para ver solo los libros
        WebElement opcionLibros = driver.findElement(By.xpath("//*[@title='Libros']"));
        opcionLibros.click();
    
        // bucle para sacar los datos de cada página
        
        do{
            
        try {
            Thread.sleep(5000);
        } 
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        
        // Obtener los elementos de la página           
        List<WebElement> listaElementos = driver.findElements(By.xpath("//*[contains(@class, 'product-preview')]"));
        
        System.out.println("Número de elementos de la lista: " + listaElementos.size() );
        
        for (int i=0; i<listaElementos.size() ; i++)    {
            //obtengo los diferentes elementos buscados          
            elementoActual = listaElementos.get(i);
            int j = i+1;
            try {
            titulo = elementoActual.findElement(By.xpath("//*[@tabindex='25']"));
            precio = elementoActual.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[1]/ul/li["+j+"]/div/div[2]/div[2]/span[1]"));
            autor = elementoActual.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[1]/ul/li["+j+"]/div/div[2]/div[1]/h4"));
            }
            catch(Exception e) {
                
            }
            
            System.out.println(i + ": "+titulo.getText()+"  "+precio.getText()+"  "+ autor.getText());  
        }
       
        try {
            botonSiguiente = driver.findElement(By.xpath("//*[@rel='next']"));
            if (botonSiguiente != null) botonSiguiente.click();
        }catch (Exception e) {
            System.out.println("Última página alcanzada"); botonSiguiente = null;
        }
        }
        while( botonSiguiente != null);
        driver.close();
    }

}
