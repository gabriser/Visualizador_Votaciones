package model;

import java.util.Objects;

public class Resultat {

	private Provincia provincia;
	private Comarca comarca;
	private Municipi municipi;
	private Partit partit;
	private int vots;
	private float percent;
	
	public Resultat(Provincia provincia, Comarca comarca, Municipi municipi, Partit partit, int vots, float percent) {
		this.provincia = provincia;
		this.comarca = comarca;
		this.municipi = municipi;
		this.partit = partit;
		this.vots = vots;
		this.percent = percent;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}

	public Municipi getMunicipi() {
		return municipi;
	}

	public void setMunicipi(Municipi municipi) {
		this.municipi = municipi;
	}

	public Partit getPartit() {
		return partit;
	}

	public void setPartit(Partit partit) {
		this.partit = partit;
	}

	public int getVots() {
		return vots;
	}

	public void setVots(int vots) {
		this.vots = vots;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "Resultat [provincia=" + provincia + ", comarca=" + comarca + ", municipi=" + municipi + ", partit="
				+ partit + ", vots=" + vots + ", percent=" + percent + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resultat resultat = (Resultat) o;
        return vots == resultat.vots &&
                Float.compare(resultat.percent, percent) == 0 &&
                Objects.equals(provincia, resultat.provincia) &&
                Objects.equals(comarca, resultat.comarca) &&
                Objects.equals(municipi, resultat.municipi) &&
                Objects.equals(partit, resultat.partit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provincia, comarca, municipi, partit, vots, percent);
    }
	
}
