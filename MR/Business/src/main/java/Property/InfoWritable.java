package Property;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class InfoWritable implements Writable {

	private String type;

	private String businessId;

	private String name;

	private String neighborhood;

	private String full_address;

	private String city;

	private String state;

	private String latitude;

	private String longitude;

	private String stars;

	private String reviewCount;

	private String category;

	private String open;

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		type = in.readUTF();
		businessId = in.readUTF();
		name = in.readUTF();
		neighborhood = in.readUTF();
		full_address = in.readUTF();
		city = in.readUTF();
		state = in.readUTF();
		latitude = in.readUTF();
		longitude = in.readUTF();
		stars = in.readUTF();
		reviewCount = in.readUTF();
		category = in.readUTF();
		open = in.readUTF();

	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(type);
		out.writeUTF(businessId);
		out.writeUTF(name);
		out.writeUTF(neighborhood);
		out.writeUTF(full_address);
		out.writeUTF(city);
		out.writeUTF(state);
		out.writeUTF(latitude);
		out.writeUTF(longitude);
		out.writeUTF(stars);
		out.writeUTF(reviewCount);
		out.writeUTF(category);
		out.writeUTF(open);
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getFull_address() {
		return full_address;
	}

	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	

}
