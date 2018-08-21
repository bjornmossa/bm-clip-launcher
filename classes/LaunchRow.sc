LaunchRow : Switchable {
  var clipButtons;
  var <view;

  *new {
	| clips |
	^super.new.init(clips);
  }

  init {
	| clips |
	var controlButton;

	clipButtons = clips.collect({
	  | clip, index |
	  var button;
	  button = LaunchButton(Color.red, Color.green);

	  button.onOn = {
		clip.play;
		onOn.value(this, index);
	  };

	  button.onOff = {
		clip.stop;
	  };

	  button;
	});

	controlButton = LaunchButton(Color.white, Color.black);

	controlButton.onOn = {
	  this.playAll();
	};

	controlButton.onOff = {
	  this.stopAll();
	};

	view = HLayout(
	  *clipButtons.collect({|b| b.view})++controlButton.view
	);

	^this;
  }

  playAll {
	clipButtons.do({
	  | cb |
	  cb.switchOn();
	});
  }

  stopAll {
	clipButtons.do({
	  | cb |
	  cb.switchOff();
	});
  }

  stopClip {
	| index |
	clipButtons.at(index).switchOff();
  }
}