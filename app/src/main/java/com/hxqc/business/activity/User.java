package com.hxqc.business.activity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created 胡俊杰
 * 2018/5/23.
 * Todo:
 */
public class User extends BaseObservable {

	private String firstName;
	private String lastName;
	private String AAName;
	public User(String firstName) {
		this.firstName = firstName;
	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Bindable
	public String getFirstName() {
		return this.firstName;
	}

	@Bindable
	public String getLastName() {
		return this.lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

		notifyPropertyChanged(BR.firstName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		notifyPropertyChanged(BR.lastName);
	}
	@Bindable
	public String getAAName() {
		return AAName;
	}

	public void setAAName(String AAName) {
		this.AAName = AAName;
		notifyPropertyChanged(BR.aAName);
	}

	@Override
	public String toString() {
		return "User{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", AAName='" + AAName + '\'' +
				'}';
	}
}
