package model;

import java.util.Objects;

public class Provincia {

	private String provincia;

	public Provincia(String provincia) {
		this.provincia = provincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Provincia [provincia=" + provincia + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provincia provincia1 = (Provincia) o;
        return Objects.equals(provincia, provincia1.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provincia);
    }
	
}
