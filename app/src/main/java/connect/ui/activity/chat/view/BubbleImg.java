package connect.ui.activity.chat.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import connect.ui.activity.R;
import connect.ui.activity.chat.bean.MsgDirect;
import connect.ui.activity.chat.inter.FileDownLoad;
import connect.ui.base.BaseApplication;
import connect.utils.FileUtil;
import connect.utils.glide.BlurMaskTransformation;
import connect.utils.system.SystemUtil;

public class BubbleImg extends RelativeLayout {

    private Context context;

    private boolean openBurn = false;

    private MsgDirect msgDirect;
    private ProgressBar progressBar;
    private ImageView imageView;

    public BubbleImg(Context context) {
        super(context);
        initView();
    }

    public BubbleImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BubbleImg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void initView() {
        context = getContext();

        RelativeLayout.LayoutParams proParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        proParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        progressBar = new ProgressBar(getContext());
        progressBar.setLayoutParams(proParams);
        addView(progressBar, proParams);

        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(imageView, imgParams);
    }

    private int imgWidth;
    private int imgHeight;

    public void calculateSize(float width, float height) {
        int maxDp = SystemUtil.dipToPx(160);
        if (height != 0 && width != 0) {
            double scale = (width * 1.00) / height;
            if (width >= height) {
                imgWidth = maxDp;
                imgHeight = (int) (maxDp / scale);
            } else {
                imgHeight = maxDp;
                imgWidth = (int) (maxDp * scale);
            }
        } else {
            imgWidth = maxDp;
            imgHeight = maxDp;
        }
    }


    public void loadUri(final MsgDirect direct, final String pukkey, final String msgid, final String url,final float imgwidth,final float imgheight) {
        msgDirect = direct;

        imageView.setImageBitmap(null);
        final String localPath = FileUtil.newContactFileName(pukkey, msgid, FileUtil.FileType.IMG);

        if (FileUtil.islocalFile(url) || FileUtil.isExistFilePath(localPath)) {
            progressBar.setVisibility(GONE);
            String local = FileUtil.islocalFile(url) ? url : localPath;
            calculateSize(imgwidth,imgheight);

            Glide.with(BaseApplication.getInstance())
                    .load(local)
                    .override(imgWidth, imgHeight)
                    .crossFade(1000)
                    .bitmapTransform(new CenterCrop(context), new BlurMaskTransformation(context, msgDirect == MsgDirect.From ? R.mipmap.message_box_white2x : R.mipmap.message_box_blue2x, (openBurn && msgDirect == MsgDirect.From) ? 16 : 0))
                    .into(imageView);
        } else {
            FileDownLoad.getInstance().downChatFile(url, pukkey, new FileDownLoad.IFileDownLoad() {
                @Override
                public void successDown(byte[] bytes) {
                    progressBar.setVisibility(GONE);
                    FileUtil.byteArrToFilePath(bytes, localPath);
                    loadUri(direct, pukkey, msgid, localPath,imgwidth,imgheight);
                }

                @Override
                public void failDown() {
                    Glide.with(BaseApplication.getInstance())
                            .load(R.mipmap.img_error)
                            .crossFade(1000)
                            .bitmapTransform(new CenterCrop(context),new BlurMaskTransformation(context, msgDirect == MsgDirect.From ? R.mipmap.message_box_white2x : R.mipmap.message_box_blue2x,(openBurn && msgDirect == MsgDirect.From) ? 16 : 0))
                            .into(imageView);
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    int progress = (int) (bytesWritten * 100 / totalSize);
                    progressBar.setVisibility(VISIBLE);
                    progressBar.setProgress(progress);
                }
            });
        }
    }

    public void setOpenBurn(boolean openBurn) {
        this.openBurn = openBurn;
    }
}
