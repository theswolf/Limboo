package core.september.android.limboo.views.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import core.september.android.limboo.R;


public class DrawerActivityMenuAdapter extends ArrayAdapter<String>{
	
	public final static String colors[] =
			{ "822111", "AC2B16", "CC3A21", "E66550", "EFA093", "F6C5BE" ,
			 "A46A21", "CF8933", "EAA041", "FFBC6B", "FFD6A2", "FFE6C7" ,
			 "AA8831", "D5AE49", "F2C960", "FCDA83", "FCE8B3", "FEF1D1" ,
			 "076239", "0B804B", "149E60", "44B984", "89D3B2", "B9E4D0" ,
			 "1A764D", "2A9C68", "3DC789", "68DFA9", "A0EAC9", "C6F3DE" ,
			 "1C4587", "285BAC", "3C78D8", "6D9EEB", "A4C2F4", "C9DAF8" ,
			 "41236D", "653E9B", "8E63CE", "B694E8", "D0BCF1", "E4D7F5" ,
			 "83334C", "B65775", "E07798", "F7A7C0", "FBC8D9", "FCDEE8" ,
			 "000000", "434343", "666666", "999999", "CCCCCC", "EFEFEF" };

	
	
	 static class ViewHolder {
		    public ImageView image;
		  }

	 private Activity context;	 
//	 public DrawerMenuAdapter(Activity context, String[] names) {
//		    super(context, R.layout.drawer_menu_layout_row, names);
//		    this.context = context;
//		    this.names = names;
//		  }
	 
	 public DrawerActivityMenuAdapter(Activity context) {
		    super(context, R.layout.drawer_menu_colorchooser_row, colors);
		    this.context = context;
		  }
	 
	 public static int parse(String color) {
		 return Color.parseColor("#" + color );
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
			//ImageView imageView;
		 	View rowView = convertView;
		 	  if (rowView == null) {
			      LayoutInflater inflater = context.getLayoutInflater();
			      rowView = inflater.inflate(R.layout.drawer_menu_colorchooser_row, null);
			      ViewHolder viewHolder = new ViewHolder();
			      //viewHolder.text = (TextView) rowView.findViewById(R.id.label);
			      viewHolder.image = (ImageView ) rowView.findViewById(R.id.icon);
			      
			      rowView.setTag(viewHolder);
			    }
		 	  
		 	 ViewHolder holder = (ViewHolder) rowView.getTag();
		 	 holder.image.setBackgroundColor(parse(colors[position] ));

			return rowView;
		}
	 
	 
	  public View _getView(int position, View convertView, ViewGroup parent) {
	    View rowView = convertView;
	    if (rowView == null) {
	      LayoutInflater inflater = context.getLayoutInflater();
	      rowView = inflater.inflate(R.layout.drawer_menu_colorchooser_row, null);
	      ViewHolder viewHolder = new ViewHolder();
	      viewHolder.image = (ImageView) rowView.findViewById(R.id.icon);
	      rowView.setTag(viewHolder);
	    }

	    ViewHolder holder = (ViewHolder) rowView.getTag();
	    String s = colors[position];
	    
	    
	 

	    return rowView;
	  }

}
