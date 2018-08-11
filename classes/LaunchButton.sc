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

LaunchButton {
  var onColor;
  var offColor;
  var size;
  var isActive;

  var <view;

  var >onOn;
  var >onOff;

  *new {
	| onClr, offClr, sz |
	^super.new.init(onClr, offClr, sz);
  }

  init {
	| onClr, offClr, sz |

	onColor = onClr;
	offColor = offClr;

	size = sz;
	isActive = false;

	view = UserView();
	view.bounds = Rect(0, 0, ~size, ~size);

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

  draw {
	if (isActive,
	  {Pen.fillColor = onColor;},
	  {Pen.fillColor = offColor;}
	);
	
	Pen.fillRect(Rect(0, 0, size, size));
  }
}

