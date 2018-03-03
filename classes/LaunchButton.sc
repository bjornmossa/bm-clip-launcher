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
	var <>index,
	onColor,
	offColor,
	<>view,
	<>isActive,
	<>container,
	<>size;

	*new {
		|i, parent, bounds, size, container|
		^super.new.init(i, parent, bounds, size, container);
	}

	init {
		|i, parent, bounds, size, container|
		this.size = size;
		this.container = container;
		this.index = i;
		this.isActive = false;
		onColor = Color.red;
		offColor = Color.gray;
		this.view = UserView(parent, bounds);
		this.view.resize = 5;
		this.view.background = Color.rand;
		this.view.drawFunc = {this.draw};
		this.view.action = {this.action;};
		this.view.mouseDownAction = {
			this.isActive = this.isActive.not;
			this.view.doAction;
		};
		^this;
	}

	switchOn {
		this.isActive = true;
		this.view.doAction(this.isActive);
	}

	switchOff {
		this.isActive = false;
		this.view.doAction(this.isActive);
	}

	action {
		if (this.isActive,
			{
				this.container.ping(this.index);
				this.container.playPattern(this.index);
			},
			{this.container.stopPattern(this.index)}
		);
		this.view.refresh;
	}

	draw {
		if (this.isActive,
			{Pen.fillColor = onColor;},
			{Pen.fillColor = offColor;}
		);
			
		Pen.fillRect(Rect(0, 0, this.size, this.size));
	}
}

