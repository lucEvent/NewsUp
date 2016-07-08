package com.lucevent.newsup.view.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.sports.util.Sport;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.SportsManager;
import com.lucevent.newsup.view.adapter.LeagueTableView;
import com.lucevent.newsup.view.dialog.SectionsDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SportFragment extends android.app.Fragment {

    public static SportFragment instanceFor(int sport_code)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(AppCode.SEND_SPORT_CODE, sport_code);

        SportFragment fragment = new SportFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private SportsManager dataManager;
    private SectionsDialog sectionsDialog;

    private LeagueTableView leagueTableView;

    private Sport currentSport;

    private FloatingActionButton btn_sections;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler(this);
        dataManager = new SportsManager(getActivity(), handler);

        int sport_code = getArguments().getInt(AppCode.SEND_SPORT_CODE);
        currentSport = AppData.getSportByCode(sport_code);

        Sections sections = currentSport.sections;
        sectionsDialog = new SectionsDialog(getActivity(), sections, onSectionSelected);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.f_sports_view, container, false);

        leagueTableView = (LeagueTableView) view.findViewById(R.id.table);

        btn_sections = (FloatingActionButton) view.findViewById(R.id.button_sections);
        btn_sections.setOnClickListener(onSectionsAction);

        dataManager.getContent(currentSport, null);

        return view;
    }

    static class Handler extends android.os.Handler {

        private final WeakReference<SportFragment> context;

        Handler(SportFragment context)
        {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg)
        {
            SportFragment service = context.get();
            switch (msg.what) {
                case AppCode.SPORT_LEAGUE_TABLE:
                    service.leagueTableView.setTable((ArrayList<TableRow>) msg.obj);
                    break;
                case AppCode.NO_INTERNET:
                    Snackbar.make(service.leagueTableView, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
                    break;
                case AppCode.ERROR:
                    AppSettings.printerror("[SF] Error received by the Handler", null);
                    break;
                default:
                    AppSettings.printerror("[SF] OPTION UNKNOWN: " + msg.what, null);
            }
        }
    }

    private View.OnClickListener onSectionsAction = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            sectionsDialog.show();
        }
    };

    private View.OnClickListener onSectionSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Section section = (Section) v.getTag();
            dataManager.getContent(currentSport, section);

            sectionsDialog.dismiss();
        }
    };

}
