package com.afs.ezra.simpletype.provider.themes.model;

import java.awt.Color;

public interface Theme {
	public Long getId();

	public void setId(Long l);

	public String getName();

	public void setName(String s);

	public Color getBackgroundMain();

	public void setBackgroundMain(Color i);

	public Color getBackgroundSecondary();

	public void setBackgroundSecondary(Color i);

	public Color getAccent();

	public void setAccent(Color i);

	public Color getAccentLight();

	public void setAccentLight(Color i);

	public Color getNeutral();

	public void setNeutral(Color i);

	public Color getNotTyped();

	public void setNotTyped(Color i);

	public Color getError();

	public void setError(Color i);

	public Color getCorrect();

	public void setCorrect(Color i);
}
