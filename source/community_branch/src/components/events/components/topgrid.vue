<template>
  <div class="absoult">
    <div class="top_event_father">
      <div v-for="item in eventnumdata" :key="item.id">
        <div class="top_event_title">{{ item.content }}</div>
        <div class="top_num_father">
          <div v-for="(i, ind) in item.num" :key="ind" class="top_num">
            <div>{{ i }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  eventNumber,
  getHandleAndConclude
} from "@/api/communityEvent/community_event";
export default {
  data() {
    return {
      eventnumdata: [
        {
          id: 0,
          content: " 待受理 (件)",
          name: "0",
          num: 0
        },
        {
          id: 1,
          content: "今日受理 (件)",
          name: "1",
          num: 0
        },
        {
          id: 2,
          content: "今日办结 (件)",
          name: "2",
          num: 0
        },
        {
          id: 3,
          content: "累计办结 (件)",
          name: "8",
          num: 0
        }
      ],
      shi: 12
    };
  },
  mounted() {
    this.eventNumers();
    this.getHandleAndConclude_();
    this.$Notice.$on("getEventslist", msg => {
      this.eventNumers();
    });
    this.$Notice.$on("getEventschange", msg => {
      this.eventNumers();
    });
  },
  watch: {
    eventnumdata: {
      handler(newVal) {
        this.eventnumdata = newVal;
      },
      deep: true
    }
  },
  filters: {
    eventnum() {
      let num = null;
      this.eventnumdata.forEach((val, i) => {
        num = val.num;
        if (num.length < 4) {
          num = ("0000" + num).slice(-4).split("");
        }
        val.num = num;
      });
    }
  },
  methods: {
    eventnum() {
      let num = null;
      this.eventnumdata.forEach((val, i) => {
        num = String(val.num);
        if (num.length < 4) {
          num = ("0000" + num).slice(-4).split("");
        }
        val.num = num;
      });
    },
    getHandleAndConclude_() {
      getHandleAndConclude({
        communityId: "0355931e-a70b-11eb-a21e-0050569ba051"
      }).then(res => {
        console.log(res, "=====");
      });
    },
    eventNumers() {
      eventNumber().then(res => {
        if (res) {
          let leijibanjie = 0;
          let a = 0;
          let b = 0;
          let c = 0;
          res.forEach((val, ind) => {
            if (val.name === "0") {
              a = Number(val.num);
            }
            if (val.name === "1") {
              b += Number(val.today);
            }
            if (val.name === "2") {
              b += Number(val.today);
            }
            if (val.name === "3") {
              b += Number(val.today);
              c += Number(val.today);
              leijibanjie = leijibanjie + Number(val.num);
            }
            if (val.name === "4") {
              b += Number(val.today);
              c += Number(val.today);
              leijibanjie = leijibanjie + Number(val.num);
            }
          });
          this.eventnumdata[0].num = a;
          this.eventnumdata[1].num = b;
          this.eventnumdata[2].num = c;
          this.eventnumdata[3].num = leijibanjie;
        }
        this.eventnum();
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.absoult {
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translateX(-50%);
  width: 43%;
}
.top_event_father {
  width: 100%;
  display: flex;
  justify-content: space-between;
  > div {
    width: 14%;
    .top_event_title {
      text-align: center;
      letter-spacing: 1px;
      color: #d0e3e6;
      font-size: 18px;
      font-family: YouSheBiaoTiHei;
      font-weight: 400;
      background: linear-gradient(180deg, #acd0de 0.146484375%, #ffffff 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }
    .top_num_father {
      margin-top: 13px;
      display: flex;
      justify-content: space-around;
      .top_num {
        width: 26px;
        height: 43px;

        border-radius: 6px;
        background: url(../../../assets/images/events/数字bg.png) no-repeat;
        background-size: 100% 100%;
        background-position: 0px 0px;
        position: relative;

        div {
          width: 100%;
          height: 100%;
          font-size: 36px;
          text-align: center;
          line-height: 38px;
          position: absolute;
          top: 7%;
          left: 3%;
          font-family: DINCondensed-Bold;
          font-weight: bold;
          color: #d0e3e6;
          background: linear-gradient(0deg, #ffd8a4 0.146484375%, #ffffff 100%);
          -webkit-background-clip: text;
          -webkit-text-fill-color: transparent;
        }
      }
    }
  }
}
</style>
