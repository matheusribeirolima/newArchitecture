package br.com.valecard.test.view.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.valecard.test.R;
import br.com.valecard.test.databinding.ItemCampaignListBinding;
import br.com.valecard.test.model.response.CampaignResponse;
import br.com.valecard.test.view.BaseViewHolder;

public class ListCampaignsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final String ID_CAMPAIGN = "id_campaign";
    private List<CampaignResponse> campaignResponses;

    ListCampaignsAdapter() {
        this.campaignResponses = new ArrayList<>();
    }

    public void setItems(List<CampaignResponse> campaignResponses) {
        this.campaignResponses.clear();
        this.campaignResponses.addAll(campaignResponses);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemCampaignListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return campaignResponses.size();
    }

    public class MyViewHolder extends BaseViewHolder {

        ItemCampaignListBinding binding;

        MyViewHolder(ItemCampaignListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(v -> {
                switch (campaignResponses.get(getAdapterPosition()).getCampaignName()) {
                    case DAILY:
                        Toast.makeText(itemView.getContext(), "DAILY!", Toast.LENGTH_SHORT).show();
                        break;
                    case BIRTHDAY:
                        Toast.makeText(itemView.getContext(), "BIRTHDAY!", Toast.LENGTH_SHORT).show();
                        break;
                    case CHRONOS:
                        Toast.makeText(itemView.getContext(), "CHRONOS!", Toast.LENGTH_SHORT).show();
                        break;
                    case PROGRESSIVE:
                        Toast.makeText(itemView.getContext(), "PROGRESSIVE!", Toast.LENGTH_SHORT).show();
                        break;
                    case ALWAYS_BACK:
                        Toast.makeText(itemView.getContext(), "ALWAYS_BACK!", Toast.LENGTH_SHORT).show();
                        break;
                    case SEGMENTED:
                        Toast.makeText(itemView.getContext(), "SEGMENTED!", Toast.LENGTH_SHORT).show();
                        break;
                    case COLETIVE:
                        Toast.makeText(itemView.getContext(), "COLETIVE!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            });
        }

        @Override
        public void onBind(int position) {
            final CampaignResponse campaignResponse = campaignResponses.get(position);

            binding.tvNameCampaign.setText(campaignResponse.getCampaignName().getId());
            binding.tvTimeCampaign.setText(campaignResponse.getExpirationTime().equals("0") ?
                    "Não expira" : campaignResponse.getExpirationTime());

            switch (campaignResponse.getCampaignName()) {
                case DAILY:
                    binding.ivCampaign.setImageResource(R.drawable.ic_daily);
                    break;
                case BIRTHDAY:
                    binding.ivCampaign.setImageResource(R.drawable.ic_birthday);
                    break;
                case CHRONOS:
                    binding.ivCampaign.setImageResource(R.drawable.ic_chronos);
                    break;
                case PROGRESSIVE:
                    binding.ivCampaign.setImageResource(R.drawable.ic_progressive);
                    break;
                case ALWAYS_BACK:
                    binding.ivCampaign.setImageResource(R.drawable.ic_always_back);
                    break;
                case SEGMENTED:
                    binding.ivCampaign.setImageResource(R.drawable.ic_segmented);
                    break;
                case COLETIVE:
                    binding.ivCampaign.setImageResource(R.drawable.ic_coletive);
                    break;
                default:
                    break;
            }
        }
    }
}
