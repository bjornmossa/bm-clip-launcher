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
	var <>buttons;

	*new {
		|parent, bounds, itemsCount|
		^super.new.init(parent, bounds, itemsCount);
	}

	init {
		|parent, bounds, itemsCount|
		var height, buttonViews;
		height = bounds.height / itemsCount;
		this.buttons = itemsCount.collect({
			|i|
			LaunchButton(i, parent, bounds, height, this);
		});
		buttonViews = this.buttons.collect({|button| button.view})
		^VLayout(*buttonViews);
	}

	ping {
		|index|
		this.buttons.do({
			|button|
			if (button.index != index && button.isActive,
				{button.switchOff;}
			);
		});
	}
}