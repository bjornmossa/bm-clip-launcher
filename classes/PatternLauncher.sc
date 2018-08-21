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
		this.switchColumn(row, index);
	  };

	  clipRow.onRowPlay = {
		| row |
		this.stopRowsExept(row);
	  };

	  clipRow;
	});

	clipRowsViews = clipRows.collect({
	  | row |
	  row.view;
	})

	^VLayout(*clipRowsViews);
  }

  switchColumn {
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

  stopRowsExept {
	| row |
	clipRows.do({
	  | clipRow |
	  if(clipRow != row,
		{
		  clipRow.stopByControl();
		}
	  )
	});
  }
}
