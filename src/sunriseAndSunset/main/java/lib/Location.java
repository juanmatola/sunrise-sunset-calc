package sunriseAndSunset.main.java.lib;

public class Location {

	private Double latitude;
	private Double longitude;
	private Integer utc;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitud(Double latitud) {
		this.latitude = latitud;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitud(Double longitud) {
		this.longitude = longitud;
	}

	public Integer getUtc() {
		return utc;
	}

	public void setUtc(Integer utc) {
		this.utc = utc;
	}

	@Override
	public String toString() {
		return "Data [utc=" + utc + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
