package principal;

public class InstanciaLibro {

	String sitio;
	String tituloLibro;
	String nombreAutorLibro;
	Double precioLibro;
	Double descunetoLibro;
	
	public InstanciaLibro() {
		this.sitio = null;
		this.tituloLibro = null;
		this.nombreAutorLibro = null;
		this.precioLibro = null;
		this.descunetoLibro = null;
	}
	
	public InstanciaLibro(String sitio, String tituloLibro, String nombreAutorLibro, Double precioLibro,
			Double descunetoLibro) {
		this.sitio = sitio;
		this.tituloLibro = tituloLibro;
		this.nombreAutorLibro = nombreAutorLibro;
		this.precioLibro = precioLibro;
		this.descunetoLibro = descunetoLibro;
	}

	public String getSitio() {
		return sitio;
	}

	public void setSitio(String sitio) {
		this.sitio = sitio;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

	public String getNombreAutorLibro() {
		return nombreAutorLibro;
	}

	public void setNombreAutorLibro(String nombreAutorLibro) {
		this.nombreAutorLibro = nombreAutorLibro;
	}

	public Double getPrecioLibro() {
		return precioLibro;
	}

	public void setPrecioLibro(Double precioLibro) {
		this.precioLibro = precioLibro;
	}

	public Double getDescunetoLibro() {
		return descunetoLibro;
	}

	public void setDescunetoLibro(Double descunetoLibro) {
		this.descunetoLibro = descunetoLibro;
	}

	@Override
	public String toString() {
		return "InstanciaLibro [sitio=" + sitio + ", tituloLibro=" + tituloLibro + ", nombreAutorLibro="
				+ nombreAutorLibro + ", precioLibro=" + precioLibro + ", descunetoLibro=" + descunetoLibro + "]";
	}
}