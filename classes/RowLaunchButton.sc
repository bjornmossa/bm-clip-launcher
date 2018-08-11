RowLaunchButton : LaunchButton {
	draw {
		if (this.isActive,
			{Pen.fillColor = onColor;},
			{Pen.fillColor = offColor;}
		);
			
		Pen.fillRect(Rect(0, 0, this.size, this.width));
	}
}