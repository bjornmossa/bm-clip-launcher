// class for rendering clip launcher buttons
// Test code below
//
// **********
//(
//w = Window.new;
//w.layout = ClipColumn(w, w.view.bounds, 6);
//w.front;
//)
//
//**********

ClipColumn {
	var <>buttons,
	<>patterns,
	<>height,
	<>bounds,
	<>parent,
	<>view;

	*new {
		|parent, bounds, patterns|
		^super.new.init(parent, bounds, patterns);
	}

	init {
		|parent, bounds, patterns|
		var buttonViews;

		this.parent = parent;
		this.bounds = bounds;
		this.height = bounds.height / patterns.size;
		this.patterns = patterns;
		this.buttons = this.fill(patterns.size);
		buttonViews = this.buttons.collect({|button| button.view});
		this.view = VLayout(*buttonViews);
		^this;
	}

	fill {
		|count|
		^count.collect({
			|i|
			LaunchButton(i, this.parent, this.bounds, this.height, this);
		});
	}

	ping {
		|index|
		if (this.buttons[index].isActive,
			{
				this.switchOffAllExept(index);
				this.playPattern(index);
			},
			{
				this.stopPattern(index);
			}
		)
	}

	switchOffAllExept {
		|index|
		this.buttons.do({
			|button|
			if (button.index != index,
				{
					button.switchOff;
					this.stopPattern(button.index);
				}
			);
		});
	}

	activate {
		|button|
		this.buttons[button].switchOn;
	}

	deactivate {
		|button|
		this.buttons[button].switchOff;
	}

	playPattern {
		|index|
		this.patterns[index].play;
	}

	stopPattern {
		|index|
		this.patterns[index].stop;
	}
}