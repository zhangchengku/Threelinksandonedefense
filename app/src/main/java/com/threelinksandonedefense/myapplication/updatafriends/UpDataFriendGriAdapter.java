package com.threelinksandonedefense.myapplication.updatafriends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.lxj.matisse.CaptureMode;
import com.lxj.matisse.Matisse;
import com.lxj.matisse.MimeType;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.BaseBean;
import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.ShowImgActivity;
import com.threelinksandonedefense.myapplication.Urls;
import com.threelinksandonedefense.myapplication.monthlyeport.MinePopupWindow;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.util.ArrayList;

public class UpDataFriendGriAdapter extends BaseAdapter {
    private Context context;//上下文对象
    private ArrayList<Drawable> listPicture;// 图片集合
    private ArrayList<UpVideoBean> listImgUrl = new ArrayList<UpVideoBean>();// 选择的图片地址集合
    private int childViewPosition;
    private MinePopupWindow minePopupWindow;

    public ArrayList<UpVideoBean> getListImgUrl() {
        return listImgUrl;
    }

    public ArrayList<Drawable> getListPicture() {
        return listPicture;
    }

    private int REQUEST_CODE_CHOOSE = 66;

    public UpDataFriendGriAdapter(Context context,
                                  ArrayList<Drawable> listPicture, ArrayList<UpVideoBean> listImgUrl, int childViewPosition) {
        this.context = context;
        this.listPicture = listPicture;
        this.listImgUrl = listImgUrl;
        this.childViewPosition = childViewPosition;
    }


    @Override
    public int getCount() {
        return listPicture.size();
    }

    @Override
    public Object getItem(int position) {
        return listPicture.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.month_gridadapter_item, null);
            vh.pictureImg = (ImageView) convertView
                    .findViewById(R.id.send_topic_picture_adapter_item_img);
            vh.pictureCloseImg = (ImageView) convertView
                    .findViewById(R.id.send_topic_picture_adapter_item_close_img);
            vh.bofang = (ImageView) convertView
                    .findViewById(R.id.bofang);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final int fpos = position;
        vh.pictureCloseImg.setVisibility(View.GONE);
        int width = (Utils.getWidth((Activity) context) - Utils.dip2px(context, 10) * 3) / 4;
        LayoutParams params = new LayoutParams(width, width);
        vh.pictureImg.setLayoutParams(params);
        if (position == listPicture.size() - 1) {
            vh.pictureImg.setImageDrawable(listPicture.get(position));
        } else {
            if (listImgUrl.get(fpos).getPic().startsWith("http")) {
                Glide.with(context)
                        .load(listImgUrl.get(fpos).getPic())
                        .into(vh.pictureImg);
            } else {
                vh.pictureImg.setImageDrawable(listPicture.get(position));
            }
        }

//        vh.pictureImg.setImageDrawable(listPicture.get(position));
        if (position == listPicture.size() - 1) {
            vh.pictureCloseImg.setVisibility(View.GONE);
            vh.bofang.setVisibility(View.GONE);
            if (context instanceof UpDataFriendsActivity) {
                vh.pictureImg.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (minePopupWindow == null) {
                            minePopupWindow = new MinePopupWindow((UpDataFriendsActivity) context, itemOnClick);
                        }
                        minePopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
                        Utils.hideInputWindow((UpDataFriendsActivity) context);
                    }
                });
            }
        } else {
            vh.pictureImg.setOnClickListener(null);
            if (Utils.isNull(listImgUrl.get(fpos).getVideo())) {
                vh.bofang.setVisibility(View.GONE);
            } else {
                vh.bofang.setVisibility(View.VISIBLE);
            }
            vh.pictureCloseImg.setVisibility(View.VISIBLE);
            // 图片右上方的删除图片的按钮
            vh.pictureCloseImg.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!Utils.isNull(listImgUrl.get(fpos).getCJID())) {
                        OkGo.<String>post(Urls.SERVER + "GDSTYF/DeleteFiles")
                                .params("guid", listImgUrl.get(fpos).getCJID())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        BaseBean baseInfo = JSON.parseObject(response.body(), BaseBean.class);
                                        if (baseInfo != null) {
                                            if ("1".equals(baseInfo.getSTATE())) {

                                            }
                                        }
                                    }
                                });
                    }
                    listImgUrl.remove(fpos);
                    listPicture.remove(fpos);
                    v.setOnClickListener(null);
                    notifyDataSetChanged();
                }
            });
            vh.pictureImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, ShowImgActivity.class);
//                    intent.putExtra("img2", listImgUrl);
//                    intent.putExtra("position", fpos);
//                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        ImageView pictureImg;//选择的图片
        ImageView pictureCloseImg;//关闭按钮
        ImageView bofang;//关闭按钮
    }

    @Override
    public int getViewTypeCount() {
        return listPicture.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 自定义pop监听
     */
    private OnClickListener itemOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mine_camera_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromCamera();
                    break;
                case R.id.mine_photo_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromPhoto();
                    break;
                case R.id.mine_cancel_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 从相册获取
     */
    public void getPicFromPhoto() {
        ((UpDataFriendsActivity) context).setChildViewPosition(childViewPosition);
        Matisse.from((UpDataFriendsActivity) context)
                .choose(MimeType.ofImage()) //显示所有文件类型，比如图片和视频，
                .isCrop(true)//开启裁剪，默认不开启
                .maxSelectable(1)
                .forResult(REQUEST_CODE_CHOOSE); //请求码
    }

    /**
     * 照相获取
     */
    public void getPicFromCamera() {
        ((UpDataFriendsActivity) context).setChildViewPosition(childViewPosition);
        Matisse.from((UpDataFriendsActivity) context)
                .jumpCapture(CaptureMode.All)//只拍照片
                .isCrop(true)
                .forResult(REQUEST_CODE_CHOOSE);
    }

}
