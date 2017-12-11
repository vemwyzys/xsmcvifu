/**
 * Created by lenovo on 2016/3/26.
 */
function resizeImage(obj)
{
    var imgW = obj.width;
    var imgH = obj.height;

    var divp = obj.parentNode;//父节点

    var divW = divp.style.width || divp.clientWidth || divp.offsetWidth || divp.scrollWidth;
    var divH = divp.style.height || divp.clientHeight || divp.offsetHeight || divp.scrollHeight;

    var maxW = parseInt(divW);
    var maxH = parseInt(divH);

    var scaleW = imgW/maxW;
    var scaleH = imgH/maxH;

    var scale = 1;

    if(scaleW > 1 && scaleH <= 1)
    {
        scale = 1/scaleW;
    }

    if(scaleH > 1 && scaleW <= 1)
    {
        scale = 1/scaleH;
    }

    if(scaleH >= 1 && scaleW >= 1)
    {
        if(scaleH >= scaleW)
        {
            scale = 1/scaleW;
        }
        else
        {
            scale = 1/scaleH;
        }
    }

    if(scaleH < 1 && scaleW < 1)
    {
        if(scaleH >= scaleW)
        {
            scale = 1/scaleW;
        }
        else
        {
            scale = 1/scaleH;
        }
    }

    imgW *= scale;
    imgH *= scale;

    obj.width = imgW;
    obj.height = imgH;

    var imgX = 0;
    var imgY = 0;

    imgX = (maxW - imgW)/2;
    imgY = (maxH - imgH)/2;

    obj.style.marginLeft = imgX + "px";
    obj.style.marginTop = imgY + "px";

}
function resizeImage2(obj)
{
    var imgW = obj.width;
    var imgH = obj.height;

    var divp = obj.parentNode;//父节点

    var divW = divp.style.width || divp.clientWidth || divp.offsetWidth || divp.scrollWidth;
    var divH = divp.style.height || divp.clientHeight || divp.offsetHeight || divp.scrollHeight;

    var minW = parseInt(divW);
    var minH = parseInt(divH);

    var scaleW = imgW/minW;
    var scaleH = imgH/minH;

    var scale = 1;

    if(scaleW > 1 && scaleH <= 1)
    {
        scale = 1/scaleW;
    }

    if(scaleH > 1 && scaleW <= 1)
    {
        scale = 1/scaleH;
    }

    if(scaleH >= 1 && scaleW >= 1)
    {
        if(scaleH >= scaleW)
        {
            scale = 1/scaleW;
        }
        else
        {
            scale = 1/scaleH;
        }
    }

    if(scaleH < 1 && scaleW < 1)
    {
        if(scaleH >= scaleW)
        {
            scale = 1/scaleW;
        }
        else
        {
            scale = 1/scaleH;
        }
    }

    imgW *= scale;
    imgH *= scale;

    obj.width = imgW;
    obj.height = imgH;

    var imgX = 0;
    var imgY = 0;

    imgX = (minW - imgW)/2;
    imgY = (minH - imgH)/2;

    obj.style.marginLeft = imgX + "px";
    obj.style.marginTop = imgY + "px";

}

