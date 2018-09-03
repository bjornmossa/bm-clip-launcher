ColorScheme {
  classvar <background;
  classvar <enabled;
  classvar <enabled2;
  classvar <disabled;
  classvar <disabled2;

  *new {
	| bgColor, enabledColor, enabled2Color, disabledColor, disabled2Color |
	^super.new.init(
	  bgColor,
	  enabledColor,
	  enabled2Color,
	  disabledColor,
	  disabled2Color
	);
  }

  init {
	| bgColor, enabledColor, enabled2Color, disabledColor, disabled2Color |
	background = bgColor;
	enabled = enabledColor;
	enabled2 = enabled2Color;
	disabled = disabledColor;
	disabled2 = disabled2Color;
  }
}