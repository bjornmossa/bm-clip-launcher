LaunchRow : Switchable {
  var clipButtons;
  var <view;
  var >onRowPlay;
  var controlButton;

  *new {
	| clips |
	^super.new.init(clips);
  }

  init {
	| clips |

	clipButtons = clips.collect({
	  | clip, index |
	  var button;
	  button = LaunchButton(ColorScheme.enabled, ColorScheme.disabled);

	  button.onOn = {
		clip.play;
		onOn.value(this, index);
	  };

	  button.onOff = {
		clip.stop;
	  };

	  button;
	});

	controlButton = LaunchButton(ColorScheme.enabled2, ColorScheme.disabled2);

	controlButton.onOn = {
	  this.playAll();
	  onRowPlay.value(this);
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

  stopByControl {
	controlButton.switchOff();
  }

  stopClip {
	| index |
	clipButtons.at(index).switchOff();
  }
}