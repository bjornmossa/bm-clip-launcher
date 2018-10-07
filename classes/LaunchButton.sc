// basic class for drawing rect within UserView class
// Test code below
//
// **********
// (
// ~bu = LaunchButton(Color.red, Color.green, 50);

// ~bu.onOn = {"on".postln};
// ~bu.onOff = {"off".postln};

// ~gui = Window();

// ~gui.layout = HLayout(
//   ~bu.view
// );

// ~gui.front;
// )
//**********

LaunchButton : Switchable {
  var <>onColor;
  var <>offColor;
  var isActive;
  var <view;
  var <>pattern;

  *new {
	| onClr, offClr |
	^super.new.init(onClr, offClr);
  }

  init {
	| onClr, offClr |
	onColor = onClr;
	offColor = offClr;

	isActive = false;
	view = UserView();
	view.drawFunc = {this.draw};

	view.mouseDownAction = {
	  isActive = isActive.not;

	  if(isActive,
		{
		  onOn.value;
		},
		{
		  onOff.value;
		}
	  );
	  view.refresh;
	};
	^this;
  }

  switchOn {
	isActive = true;
	onOn.value;
	view.refresh;
  }

  switchOff {
	isActive = false;
	onOff.value;
	view.refresh;
  }

  draw {
	if (isActive,
	  {Pen.fillColor = onColor;},
	  {Pen.fillColor = offColor;}
	);
	
	Pen.fillRect(Rect(0, 0, view.bounds.width, view.bounds.height));
  }
}

