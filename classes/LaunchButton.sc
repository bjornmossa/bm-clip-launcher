// basic class for drawing rect within UserView class
// Test code below
//
// **********
// w = Window.new();
// LaunchButton(w, w.view.bounds);
// w.front;
//
//**********

LaunchButton {
	var <>index, onColor, offColor, <>view, <>isActive;

	*new {
		|i, parent, bounds, size, container|
		^super.new.init(i, parent, bounds, size, container);
	}

	init {
		|i, parent, bounds, size, container|
		this.index = i;
		this.isActive = false;
		onColor = Color.red;
		offColor = Color.gray;
		this.view = UserView(parent, bounds);
		this.view.resize = 5;
		this.view.background = Color.rand;
		this.view.drawFunc = {
			Pen.strokeColor = Color.black;
			if (isActive,
				{Pen.fillColor = onColor;},
				{Pen.fillColor = offColor;}
			);
			
			Pen.fillRect(Rect(0, 0, size, size));
		};
		this.view.action = {
			if (this.isActive,
				{container.ping(this.index);}
			);
			this.view.refresh;
		};
		this.view.mouseDownAction = {
			this.isActive = this.isActive.not;
			this.view.doAction;
		};
		^this;
	}

	switchOff {
		this.isActive = false;
		this.view.doAction(this.isActive);
	}
}

