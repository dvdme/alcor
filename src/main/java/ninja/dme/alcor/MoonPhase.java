package ninja.dme.alcor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MoonPhase {

	private final String NEW_MOON = "New Moon";
	private final String WAXING_CRESCENT = "Waxing Crescent";
	private final String FIRST_QUARTER = "First Quarter";
	private final String WAXING_GIBBOUS = "Waxing Gibbous";
	private final String FULL_MOON = "Full Moon";
	private final String WANNING_GIBBOUS = "Wanning Gibbous";
	private final String THIRD_QUARTER = "Third Quarter";
	private final String WANNING_CRESCENT = "Wanning Crescent";

	private int year;
	private int month;
	private int day;
	private Date date;
	private Calendar calendar;
	private double age = -1;
	private double percentage = -1;
	private boolean crescent;

	public MoonPhase(Date date) {
		this.date = date;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		this.calendar = calendar;
		setDate(year, month, day);
	}

	public MoonPhase(int year, int month, int day) {
		setDate(year, month, day);
	}
	
	public MoonPhase nextDay() {
		this.calendar.add(Calendar.DAY_OF_MONTH, 1);
		int year = this.calendar.get(Calendar.YEAR);
		int month = this.calendar.get(Calendar.MONTH) + 1;
		int day = this.calendar.get(Calendar.DAY_OF_MONTH);
		return new MoonPhase(year, month, day);
	}

	public String phaseName() {

		if (this.crescent) {
			return phaseForCrescent();
		} else {
			return phaseForWanning();
		}
	}
	public String ping() {
		return "pong";
	}

	private void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.date = new Date();
		calculate();
	}

	private String phaseForCrescent() {
		if (percentage >= 0 && percentage < 3) {
			return NEW_MOON;
		}
		if (percentage >= 3 && percentage < 35) {
			return WAXING_CRESCENT;
		}
		if (percentage >= 35 && percentage < 66) {
			return FIRST_QUARTER;
		}
		if (percentage >= 66 && percentage < 97) {
			return WAXING_GIBBOUS;
		}
		if (percentage >= 97 && percentage <= 100) {
			return FULL_MOON;
		} else {
			return null;
		}
	}

	private String phaseForWanning() {
		if (percentage < 100 && percentage > 96 && !crescent) {
			return FULL_MOON;
		}
		if (percentage <= 96 && percentage > 66 && !crescent) {
			return WANNING_GIBBOUS;
		}
		if (percentage <= 65 && percentage > 35 && !crescent) {
			return THIRD_QUARTER;
		}
		if (percentage <= 35 && percentage > 3 && !crescent) {
			return WANNING_CRESCENT;
		}
		if (percentage <= 3 && percentage >= 0 && !crescent) {
			return NEW_MOON;
		} else {
			return null;
		}
	}
	
	private void calculate() {
		setMoonAge();
		setPercentage();
		setCrescent();
	}

	private void setMoonAge() {
		double r = this.year % 100;
		double age = 0;

		r %= 19;
		if (r > 9) {
			r -= 19;
		}
		r = ((r * 11) % 30) + this.month + this.day;
		if (this.month < 3) {
			r += 2;
		}
		r -= ((this.year < 2000) ? 4 : 8.3);
		r = Math.floor(r + 0.5) % 30;
		age = (r < 0) ? r + 30 : r;
		this.age = age;
	}

	private void setCrescent() {
		
		if(this.percentage < 0) {
			setPercentage();
		}

		if (this.percentage >= this.percentage) {
			this.crescent = true;
		} else {
			this.crescent = false;
		}

	}

	private void setPercentage() {
		if(this.age < 0) {
			setMoonAge();
		}
		
		double age_per = 0.0;
		double percentage = 0.0;

		age_per = ((this.age) / 14) * 100;
		if (this.age >= 15) {
			this.age = 29 - this.age;
			age_per = ((this.age) / 14) * 100;
		}

		percentage = age_per * 100;
		percentage = Math.round(percentage);
		percentage = percentage / 100;

		this.percentage = percentage;

	}

}
