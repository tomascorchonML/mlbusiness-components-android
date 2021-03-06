package com.mercadolibre.android.mlbusinesscomponents.components.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.squareup.picasso.Picasso;

public class MLBusinessInfoView extends ConstraintLayout {

    private final TextView description;
    private final AppCompatImageView icon;
    private MLBusinessInfoData businessInfoData;

    public MLBusinessInfoView(final Context context) {
        this(context, null);
    }

    public MLBusinessInfoView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessInfoView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.ml_view_business_info, this);

        description = findViewById(R.id.description);
        icon = findViewById(R.id.icon);
    }

    private void configLoyaltyHeaderView() {
        final Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.info_icon_background);
        if (unwrappedDrawable != null) {
            final Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(businessInfoData.getIconBackgroundHexaColor()));
            icon.setBackground(wrappedDrawable);
        }

        description.setText(businessInfoData.getDescription());

        Picasso.with(getContext()).load(businessInfoData.getIcon()).into(icon);
    }

    public void init(@NonNull final MLBusinessInfoData businessInfoData) {
        this.businessInfoData = businessInfoData;
        configLoyaltyHeaderView();
    }

    public void updateWithModel(@NonNull final MLBusinessInfoData businessInfoData) {
        init(businessInfoData);
    }
}