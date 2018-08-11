PatternLauncher {
	*new {
		|parent, bounds, patternArray|
		^super.new.init(parent, bounds, patternArray);
	}

	init {
		|parent, bounds, patternArray|
		var view, clipCols, clipColsViews, controlCol, cols;
		clipCols = patternArray.collect({
			|pattern|
			ClipColumn(parent, bounds, pattern);
		});
		clipColsViews = clipCols.collect({
			|c|
			c.view;
		});
		controlCol = ControlColumn(parent, bounds, patternArray[0].size, clipCols);
		cols = clipColsViews++controlCol;
		^HLayout(*cols);
	}  
}
