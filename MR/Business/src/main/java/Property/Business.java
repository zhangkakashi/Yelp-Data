package Property;



import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName(value = "business_id")
public class Business {
	
		@JsonProperty("type")
		public String type;
		
		@JsonProperty("business_id")
		public String businessId;
		
		@JsonProperty("name")
		public String name;
		
		@JsonProperty("neighborhoods")
		public List<String>  neighborhood;
		
		@JsonProperty("full_address")
		public String full_address;
		@JsonProperty("city")
		public String city;
		@JsonProperty("state")
		public String state;
		@JsonProperty("latitude")
		public String latitude;
		@JsonProperty("longitude")
		public String longitude;
		@JsonProperty("stars")
		public String stars;
		@JsonProperty("review_count")
		public String reviewCount;
		@JsonProperty("categories")
		public List<String> category;
		//public String category;
		@JsonProperty("open")
		public boolean open;
}
