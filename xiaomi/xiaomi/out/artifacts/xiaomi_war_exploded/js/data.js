initChina();
function initChina(){
    let url = "https://restapi.amap.com/v3/config/distri" +
        "ct?keywords=中华人民共和国&subdistrict=3&key=a76464eefed8e9a35fe5c8c4a83bc6ea";
    let china;
    $.get(url,function (data){
        china = data;
        //取出所有省份
        let provinces = data.districts[0].districts;

        //加载省信息
        for(let province of provinces){
            $('#province')
                .append("<option value="+province.name+">"+province.name+"</option>");
        }

        //先初始化默认的省市区
        let firstProvince = data.districts[0].districts[0];
        //取出该省的市
        let cities = firstProvince.districts;
        //加载市
        let cityContent = "";
        for(let city of cities){
            cityContent += "<option value="+city.name+">"+city.name+"</option>";
        }
        $('#city').html(cityContent);

        let firstCity = cities[0];
        //加载县区
        let regions = firstCity.districts;
        let regionContent = "";
        for(let region of regions){
            regionContent += "<option value="+region.name+">"+region.name+"</option>";
        }
        $('#region').html(regionContent);
    },'json');

    //选择省
    $('#province').change(function (){
        //取出所有省份
        let provinces = china.districts[0].districts;
        for(let province of provinces){
            if($(this).val() == province.name){
                //取出该省下的所有市
                let cities = province.districts;
                console.log(cities);
                let content = "";
                //判断上海市、天津市、北京市、重庆市三个直辖市
                if(cities.length == 1){
                    let regions = cities[0].districts;
                    for(let region of regions){
                        content += "<option value="+region.name+">"+region.name+"</option>";
                    }
                    $('#city').html(content);
                    //region不显示
                    $('#region').css('display','none');
                }else{
                    $('#region').css('display','inline');
                    for(let city of cities){
                        content += "<option value="+city.name+">"+city.name+"</option>";
                    }
                    $('#city').html(content);
                }

                //初始化县区
                let text = "";
                let regions = cities[0].districts;
                for(let region of regions){
                    text += "<option value="+region.name+">"+region.name+"</option>";
                }
                $('#region').html(text);
            }
        }
    });

    //选择市
    $('#city').change(function (){
        //取出所有省份
        let provinces = china.districts[0].districts;
        let content = "";
        for(let province of provinces){
            //取出该省下的所有市
            let cities = province.districts;
            for(let city of cities){
                //取出市下的所有区
                let regions = city.districts;
                for(let region of regions){
                    if($(this).val() == city.name){
                        content += "<option value="+region.name+">"+region.name+"</option>";
                    }
                }
            }
        }
        $('#region').html(content);
    });
}