package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fs.sha28.R;
import com.fs.sha28.SponsorScreen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.Sponsor;
import utils.Global;

/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    private final Context mContext;
    private final List<Sponsor> mItems;
    private final SponsorScreen sponsorScreen;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView imgSponsor;
        public final View mainView;
        public SimpleViewHolder(View view) {
            super(view);
            mainView=view;
            title = (TextView) view.findViewById(R.id.title);
            imgSponsor= (ImageView) view.findViewById(R.id.imgSponsorImage);
        }
    }

    public SimpleAdapter(Context context , List<Sponsor> sponsors) {
        mContext = context;
        mItems = sponsors;
        sponsorScreen=(SponsorScreen)context;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.mainView.setOnClickListener(new OnItemClick(mItems.get(position)));
        holder.title.setText(mItems.get(position).getSponsorName());
        Picasso.with(mContext)
                .load(Global.BASE_URL_IMAGE+mItems.get(position).getSponsorLogo())
                .placeholder(R.mipmap.defualt_imag)
                .error(R.mipmap.defualt_imag)
                .into(holder.imgSponsor);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private class OnItemClick implements View.OnClickListener{
        private Sponsor sponsor;
        public OnItemClick(Sponsor sponsorL){
            sponsor=sponsorL;
        }
        @Override
        public void onClick(View view) {
            sponsorScreen.onItemClick(sponsor);
        }
    }
}