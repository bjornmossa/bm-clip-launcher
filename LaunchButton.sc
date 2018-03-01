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
	var onColor, offColor, view;

	*new {
		|parent, bounds|
		^super.new.init
	}

	init {
		|parent, bounds|
		//onColor = Color.red;
		//offColor = Color.gray;
		view = UserView(parent, bounds);
		view.resize = 5;
		view.background = Color.rand;
		view.drawFunc = {
			Pen.fillColor = Color.rand;
			Pen.fillRect(Rect(0, 0, 200, 200));
		};
		//.action_({view.refresh})
		view.mouseDownAction = {view.refresh};
		^view;
	}
}