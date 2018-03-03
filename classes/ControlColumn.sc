ControlColumn {
	var <>buttons,
	<>height,
	<>bounds,
	<>parent,
	<>clips;

	*new {
		|parent, bounds, size, clips|
		^super.new.init(parent, bounds, size, clips);
	}

	init {
		|parent, bounds, size, clips|
		var buttonViews;

		this.parent = parent;
		this.bounds = bounds;
		this.height = bounds.height / size;
		this.buttons = this.fill(size);
		this.clips = clips;
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

	playPattern {
		|index|
		this.clips.do({
			|col|
			col.activate(index);
		});
	}

	stopPattern {
		|index|
		this.clips.do({
			|col|
			col.deactivate(index);
		});
	}
	
	fill {
		|count|
		^count.collect({
			|i|
			RowLaunchButton(i, this.parent, this.bounds, this.height, this);
		});
	}
}