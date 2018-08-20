PatternLauncher {
  var clipRows;

  *new {
	|patternArray|
	^super.new.init(patternArray);
  }

  init {
	|patternArray|
	var clipRowsViews;

	clipRows = patternArray.collect({
	  | patterns, i |
	  var clipRow = LaunchRow(patterns);

	  clipRow.onOn = {
		|row, index|
		this.switchRow(row, index);
	  };

	  clipRow;
	});

	clipRowsViews = clipRows.collect({
	  | row |
	  row.view;
	})

	^VLayout(*clipRowsViews);
  }

  switchRow {
	| row, index |
	clipRows.do({
	  | clipRow |
	  if(clipRow != row,
		{
		  clipRow.stopClip(index);
		}
	  )
	});
  }
}
