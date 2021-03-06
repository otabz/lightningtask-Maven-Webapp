package com.stuff.lightningtalks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

//entity
//be careful while creating entities from schema
//business logic must not overwrite
/**
 * Topic entity. @author Tayyab
 */
@Entity
@Table(name="topic"
    ,catalog="lightningtalks"
)
@Indexed
@AnalyzerDefs({
@AnalyzerDef(name = "standardAnalyzer",

// Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),

filters = {
 // Normalize token text to lowercase, as the user is unlikely to
 // care about casing when searching for matches
 @TokenFilterDef(factory = WordDelimiterFilterFactory.class),
 @TokenFilterDef(factory = LowerCaseFilterFactory.class),
 @TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
   @Parameter(name = "pattern", value = "([^a-zA-Z0-9\\.])"),
   @Parameter(name = "replacement", value = " "),
   @Parameter(name = "replace", value = "all") }),
 @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
	      @Parameter(name = "language", value = "English")
	    })
})
})
@NamedQueries(value = { 
		@NamedQuery(name = "Topic.All", query = "SELECT t FROM Topic t WHERE t.talkDate=:talk ORDER BY t.time asc")
		})
public class Topic {

	@Id
    @Column(name="subject", unique=true, nullable=false, length=80)
	@Fields({
		  @Field(name = "subject_", index = Index.YES, store = Store.YES,
		analyze = Analyze.YES, analyzer = @Analyzer(definition = "standardAnalyzer"))
		})
	private String subject;
	
	@Column(name="description", length=120)
	private String description;
	
	@Column(name="email", nullable=false)
	private String userId;
	
	@Column(name="submission", nullable=false, length=19)
	private Date time;
	
	@Column(name="talk", nullable=false)
	private Date talkDate;
	
	@Column(name="ip", length=45)
	private String ipAddress;
	
	@Column(name="host", length=45)
	private String hostName;
	
	@Column(name="agent", length=300)
	private String userAgent;

	// requirements of a valid object must be declared while constructing it
	public Topic() {
	}

	public Topic(String subject) {
		this(subject, "", "", null);
	}

	public Topic(String subject, String description) {
		this(subject, description, "", null);
	}

	public Topic(String subject, String description, String userId) {
		this(subject, description, userId, null);
	}

	public Topic(String subject, String description, String userId, Date time) {
		this.subject = subject;
		this.description = description;
		this.userId = userId;
		Calendar cal = Calendar.getInstance();
		cal.set(1984, 9, 6);
		this.time = time == null ? cal.getTime() : time;
		this.talkDate = calculateTalkDate();
	}
	
	public Date calculateTalkDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.time);
		// year boundary case
		int submissionMonth = cal.get(Calendar.MONTH);
		int calculatedMonth = calculateMonth(cal);
		if(submissionMonth == 11 
				&& (cal.get(Calendar.MONTH) != calculatedMonth)) {
			cal.add(Calendar.YEAR, 1);
		}
		// month
		cal.set(Calendar.MONTH, calculatedMonth);
		// first tuesday
		Calendar calFromDay1 = Calendar.getInstance();
		calFromDay1.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		cal.set(Calendar.DATE, calculateFirstTuesday(calFromDay1));
		return cal.getTime();
	}
	
	public int calculateFirstTuesday(Calendar cal) {
		int day = -1;
		if(cal.get(Calendar.DAY_OF_WEEK) > 3) {
			day = 1 + 3 + (7 - cal.get(Calendar.DAY_OF_WEEK));
		} else {
			day = 1 + (3 - cal.get(Calendar.DAY_OF_WEEK));
		}
		return day;
//		date + (3 + (7-7))
//		date + (3 + (7-6))
//		date + (3 + (7-5))
//		date + (3 + (7-4))
	}
	
	public int calculateMonth(Calendar cal) {
		int month = cal.get(Calendar.MONTH) + 1;
		if(month%2 != 0) {
			cal.add(Calendar.MONTH, 1);
		} else {
			int date = cal.get(Calendar.DATE);
			Calendar calFromDay1 = Calendar.getInstance();
			calFromDay1.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
			int firstTuesday = calculateFirstTuesday(calFromDay1);
			
			if(date >= firstTuesday)
			cal.add(Calendar.MONTH, 2);
		}
		return cal.get(Calendar.MONTH);
	}

	@Override
	public boolean equals(Object other) {
		if (null == other) {
			return false;
		} else if (!(other instanceof Topic)) {
			return false;
		} else if (!((Topic) other).subject.equals(this.subject)) {
			return false;
		} else if (!((Topic) other).description.equals(this.description)) {
			return false;
		} else if (!((Topic) other).userId.equals(this.userId)) {
			return false;
		} else if (((Topic) other).time.compareTo(this.time) != 0) {
			return false;
		} else if(((Topic) other).talkDate.compareTo(this.talkDate) != 0) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return this.subject.hashCode() / 11 + this.description.hashCode() / 33
				+ this.userId.hashCode() + (int) this.time.getTime()
				+ (int) this.time.getTime();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

	public String getUserId() {
		return userId;
	}

	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd yy HH:mm");
		return format.format(this.time);
	}

	public String getTalkDate() {
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd yy");
		return format.format(this.talkDate);
	}
}
