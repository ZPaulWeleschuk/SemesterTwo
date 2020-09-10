package sait.mms.problemdomain;

public class Movie {
	private int id;
	private int duration;
	private String  title;
	private int year;
	
	public Movie(int id, int duration, String title, int year) {
		super();
		this.id = id;
		this.duration = duration;
		this.title = title;
		this.year = year;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "Movie [id=" + id + ", duration=" + duration + ", title=" + title + ", year=" + year + "]";
	}
	
	
	
}
