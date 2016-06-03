package Property;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
	@JsonProperty("type")
	public String type;
	
	@JsonProperty("business_id")
	public String businessId;
	
	@JsonProperty("stars")
	public String stars;
	
	@JsonProperty("text")
	public String text;
	
	
	@JsonProperty("votes")
	public Vote votes;
	
	public class Vote{
		@JsonProperty("funny")
		public String funny;
		
		@JsonProperty("useful")
		public String useful;
		
		@JsonProperty("cool")
		public String cool;
	}
}


	
