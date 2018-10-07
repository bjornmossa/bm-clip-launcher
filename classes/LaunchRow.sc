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
	  if(
		clip.isNil.not,
		{
		  button = LaunchButton(
			ColorScheme.enabled,
			ColorScheme.disabled
		  );

		  button.onOn = {
			if(button.pattern.isPlaying.not, {
			  button.pattern = clip.play(quant: 1);
			  onOn.value(this, index);
			});
		  };

		  button.onOff = {
			button.pattern.stop;
		  };
		},
		{
		  button = LaunchButton(ColorScheme.background, ColorScheme.background);
		}
	  );

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