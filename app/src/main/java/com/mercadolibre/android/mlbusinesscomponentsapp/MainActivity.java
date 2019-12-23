package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.common.MLBusinessInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp.MLBusinessDownloadAppView;
import com.mercadolibre.android.mlbusinesscomponents.components.crossselling.MLBusinessCrossSellingBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxData;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyHeaderView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MLBusinessLoyaltyRingView.OnClickLoyaltyRing,
        MLBusinessDiscountBoxView.OnClickDiscountBox,
        MLBusinessCrossSellingBoxView.OnClickCrossSellingBoxView,
        MLBusinessDownloadAppView.OnClickDownloadApp {

    private DiscountBoxViewItemSelectorAdapter discountBoxViewItemSelectorAdapter;
    private Spinner discountBoxItemSpinner;
    private MLBusinessDiscountBoxView discountBoxView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discountBoxViewItemSelectorAdapter = new DiscountBoxViewItemSelectorAdapter();

        MLBusinessLoyaltyRingView ringView = findViewById(R.id.loyaltyView);
        discountBoxView = findViewById(R.id.discountView);
        MLBusinessDownloadAppView downloadAppView = findViewById(R.id.downloadAppView);
        MLBusinessCrossSellingBoxView crossSellingBoxView = findViewById(R.id.crossSellingView);
        MLBusinessLoyaltyHeaderView loyaltyHeaderView = findViewById(R.id.loyaltyHeaderView);
        LinearLayout benefitContainer = findViewById(R.id.loyaltyBenefitsContainer);

        Button button = findViewById(R.id.buttonOpen);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ButtonsActivity.class);
            startActivity(intent);
        });

        MLBusinessInfoView benefitView = new MLBusinessInfoView(this);
        MLBusinessDiscountBoxDataSample mlBusinessDiscountBoxDataSample = new MLBusinessDiscountBoxDataSample();

        ringView.init(new MLBusinessLoyaltyRingDataSample(), this);

        discountBoxView.init(mlBusinessDiscountBoxDataSample, this);

        downloadAppView.init(new MLBusinessDownloadAppDataSample(), this);

        crossSellingBoxView.init(new MLBusinessCrossSellingBoxDataSample(), this);

        loyaltyHeaderView.init(new MLBusinessLoyaltyHeaderDataSample());

        benefitView.init(new MLBusinessInfoDataSample());
        benefitContainer.addView(benefitView);
        setDiscountBoxViewItemSelector();
    }

    private void setDiscountBoxViewItemSelector() {
        discountBoxItemSpinner = findViewById(R.id.item_quantity_selector_spinner);
        discountBoxItemSpinner.setAdapter(discountBoxViewItemSelectorAdapter);
        discountBoxItemSpinner.setSelection(discountBoxItemSpinner.getCount() - 1);
        discountBoxItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateItemQuantity(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });
    }

    private void updateItemQuantity(int position) {

        discountBoxView.init(new MLBusinessDiscountBoxData() {
            @Nullable
            @Override
            public String getTitle() {
                return "200 descuentos";
            }

            @Nullable
            @Override
            public String getSubtitle() {
                return "35 exclusivos nivel 3";
            }

            @NonNull
            @Override
            public List<MLBusinessSingleItem> getItems() {
                return DataSampleUtils.getItems().subList(0, position);
            }
        }, this);

    }

    @Override
    public void onClickLoyaltyButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }

    @Override
    public void onClickDiscountItem(final int index, @Nullable final String deepLink,
                                    @Nullable final String trackId) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }

    @Override
    public void OnClickCrossSellingButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }

    @Override
    public void OnClickDownloadAppButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }
}
